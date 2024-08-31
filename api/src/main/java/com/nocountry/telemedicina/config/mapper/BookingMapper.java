package com.nocountry.telemedicina.config.mapper;

import com.nocountry.telemedicina.dto.request.BookingRequestDTO;
import com.nocountry.telemedicina.dto.response.BookingResponseDTO;
import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.repository.projection.IBookingProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mapping(source = "schedules.schedulesDay",target = "schedulesDay")
    @Mapping(source = "schedules.schedulesStart",target = "schedulesStart")
    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "user.username",target = "username")
    BookingResponseDTO toBookingDTO(Booking booking);

    @Mapping(source = "schedules",target = "schedules")
    @Mapping(source = "user",target = "user")
    Booking toBooking(BookingRequestDTO dto);


    @Mapping(source = "bookingId", target = "bookingId")
    @Mapping(source = "ISchedulesProjection.schedulesDay", target = "schedulesDay")
    @Mapping(source = "ISchedulesProjection.schedulesStart", target = "schedulesStart")
    BookingResponseDTO toBookingDTO(IBookingProjection bookingProjection);
}
