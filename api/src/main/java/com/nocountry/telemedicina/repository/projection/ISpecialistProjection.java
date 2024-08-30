package com.nocountry.telemedicina.repository.projection;

import java.util.UUID;

public interface ISpecialistProjection {
    UUID getSpecialistId();
    Double getBookingPrice();
}
