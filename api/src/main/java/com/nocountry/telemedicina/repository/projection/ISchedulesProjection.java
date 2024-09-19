package com.nocountry.telemedicina.repository.projection;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ISchedulesProjection {
    Long getSchedulesId();
    LocalDate getSchedulesDay();
    LocalTime getSchedulesStart();
    ISpecialistProjection getISpecialistProjection();
}
