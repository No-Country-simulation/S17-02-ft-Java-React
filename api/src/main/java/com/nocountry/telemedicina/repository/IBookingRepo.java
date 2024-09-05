package com.nocountry.telemedicina.repository;

import com.nocountry.telemedicina.models.Booking;
<<<<<<< HEAD
=======
import com.nocountry.telemedicina.repository.projection.IBookingProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

>>>>>>> 5e5f8487634c5773f91a66eeb5e90c92024b3338
import java.util.UUID;

public interface IBookingRepo extends IGenericRepo<Booking, UUID>{

    @Query(value = "SELECT * FROM bookings WHERE user_id=:id and active=true", nativeQuery = true)
    Page<IBookingProjection> findAllByUserId(UUID id, Pageable pageable);
}
