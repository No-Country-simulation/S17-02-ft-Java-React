package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.repository.projection.IBookingProjection;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IBookingService extends ICRUDService<Booking, UUID> {
    Page<IBookingProjection> findAllByUserId(UUID id, int page, int size, String sortField, String sortOrder);

}
