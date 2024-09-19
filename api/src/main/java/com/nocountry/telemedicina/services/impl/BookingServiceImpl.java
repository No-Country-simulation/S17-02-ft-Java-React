package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.config.mapper.BookingMapper;
import com.nocountry.telemedicina.dto.request.BookingRequestDTO;
import com.nocountry.telemedicina.dto.response.BookingResponseDTO;
import com.nocountry.telemedicina.exception.BadRequestException;
import com.nocountry.telemedicina.exception.CustomException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.models.Pay;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.models.enums.State;
import com.nocountry.telemedicina.repository.*;
import com.nocountry.telemedicina.repository.projection.IBookingProjection;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.IBookingService;
import com.nocountry.telemedicina.services.IPayService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class BookingServiceImpl extends CRUDServiceImpl<Booking, UUID> implements IBookingService {

    @Autowired
    private IBookingRepo repo;

    @Autowired
    private IScheduleRepo scheduleRepo;

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private IPayRepo payRepo;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    protected IGenericRepo<Booking, UUID> getRepo() {
        return repo;
    }

    @Override
    public Page<IBookingProjection> findAllByUserId(UserPrincipal user, int page, int size, String sortField, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, getSort(sortField, sortOrder));
        return repo.findAllByUserId(user.getId(), pageable);
    }

    @Transactional
    @Override
    public BookingResponseDTO save(Booking booking, UserPrincipal user) {

        if(LocalDate.now().isAfter(booking.getSchedule().getDate())) {
            throw new BadRequestException("No se puede crear una reserva con un dia anterior a la fecha del dia de hoy");
        }
        User userNew= userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException(String.format("User not found with id: %s",user.getId())));
        booking.setUser(userNew);
        booking.setState(State.PENDING);
        booking.setBookingReason("Booking");
       try {
           Booking newBooking = repo.save(booking);
           scheduleRepo.desactiveScheduleById(newBooking.getSchedule().getScheduleId());

           return bookingMapper.toBookingDTO(newBooking);
       }catch (Exception ex) {
           throw new CustomException(500,"Error al persistir booking");
       }
    }

    @Override
    public Booking update(Booking booking, UserPrincipal user) {

      try {  Booking bookingData = repo.findById(booking.getBookingId()).orElseThrow(() -> new NotFoundException(String.format("Booking not found with id: %s ",booking.getBookingId())));
          // En caso de que el schedules ingresado sea mayo al dia de hoy lanzara la exception.
          if(LocalDate.now().isAfter(booking.getSchedule().getDate())) {
              throw new BadRequestException("No se puede crear una reserva con un dia anterior a la fecha del dia de hoy");
          }
          // En caso de que el Schedule que es reemplazado todavia no haya vencido se habilitara para poder ser tomado nuevamente.
          if(bookingData.getSchedule().getDate().isBefore(LocalDate.now())) {
              scheduleRepo.ActiveScheduleById(booking.getSchedule().getScheduleId());
          }
          // actualiza el schedule
          bookingData.setSchedule(booking.getSchedule());
          Booking bookingUpdate = repo.save(bookingData);

          // actualiza la disponibilidad del nuevo schedule
          scheduleRepo.desactiveScheduleById(bookingData.getSchedule().getScheduleId());

          return bookingUpdate;
      }catch (Exception ex) {
          throw new CustomException(500,"Error al actualizar booking");
      }
    }
    @Transactional
    @Override
    public Booking updatePayment(UUID bookingId, Boolean paymentStatus, String mpPaymentId) {
        try{
            Booking bookingData = repo.findById(bookingId).orElseThrow(() -> new NotFoundException(String.format("Booking not found with id: %s ",bookingId)));

           if(paymentStatus) {
               // actualiza el booking y persiste el pago en la db
               bookingData.setState(State.PAID);
               Pay newPayment = new Pay();
               newPayment.setBooking(bookingData);
               newPayment.setOperationNumber(mpPaymentId);
               payRepo.save(newPayment);
               repo.save(bookingData);

           }else {
               bookingData.setState(State.CANCELED);
           }
            repo.save(bookingData);
           return bookingData;

        }catch (Exception ex) {
            throw new CustomException(500,"Error al actualizar booking");
        }
    }


    private Sort getSort(String sortField, String sortOrder) {
        Sort sort = Sort.by(sortField);
        if (sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        return sort;
    }
}
