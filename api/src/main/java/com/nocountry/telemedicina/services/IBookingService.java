package com.nocountry.telemedicina.services;

import com.nocountry.telemedicina.models.Booking;
import com.nocountry.telemedicina.repository.projection.IBookingProjection;
import com.nocountry.telemedicina.security.oauth2.user.CurrentUser;
import com.nocountry.telemedicina.security.oauth2.user.UserPrincipal;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IBookingService extends ICRUDService<Booking, UUID> {
    Page<IBookingProjection> findAllByUserId(@CurrentUser UserPrincipal user, int page, int size, String sortField, String sortOrder);
    Booking save(Booking booking, @CurrentUser UserPrincipal user);
}
