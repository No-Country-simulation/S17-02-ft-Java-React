package com.nocountry.telemedicina.repository.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface IBookingProjection {
    UUID getBookingId();
    @Value("#{target.schedules}")
    ISchedulesProjection getISchedulesProjection();
}
