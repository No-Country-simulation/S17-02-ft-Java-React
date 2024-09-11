package com.nocountry.telemedicina.services.impl;

import com.nocountry.telemedicina.exception.BadRequestException;
import com.nocountry.telemedicina.exception.CustomException;
import com.nocountry.telemedicina.exception.NotFoundException;
import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.models.User;
import com.nocountry.telemedicina.repository.IBookingRepo;
import com.nocountry.telemedicina.repository.IGenericRepo;
import com.nocountry.telemedicina.repository.IScheduleRepo;
import com.nocountry.telemedicina.repository.IUserRepo;
import com.nocountry.telemedicina.repository.projection.IBookingProjection;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import com.nocountry.telemedicina.services.IBookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Booking save(Booking booking, UserPrincipal user) {

        if(booking.getSchedule().getDate().isAfter(LocalDate.now())) {
            throw new BadRequestException("No se puede crear una reserva con un dia anterior a la fecha del dia de hoy");
        }
        User userNew= userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException(String.format("User not found with id: %s",user.getId())));
        booking.setUser(userNew);
       try {
           Booking newBooking = repo.save(booking);
           scheduleRepo.desactiveScheduleById(newBooking.getSchedule().getScheduleId());
           return newBooking;
       }catch (Exception ex) {
           throw new CustomException(500,"Error al persistir booking");
       }
    }

    @Override
    public Booking update(Booking booking, UserPrincipal user) {

        Booking bookingData = repo.findById(booking.getBookingId()).orElseThrow(() -> new NotFoundException(String.format("Booking not found with id: %s ",booking.getBookingId())));
        // En caso de que el schedules ingresado sea mayo al dia de hoy lanzara la exception.
        if(booking.getSchedule().getDate().isAfter(LocalDate.now())) {
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
