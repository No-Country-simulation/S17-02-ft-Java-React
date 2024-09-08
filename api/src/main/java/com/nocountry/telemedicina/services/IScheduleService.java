package com.nocountry.telemedicina.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.nocountry.telemedicina.models.Schedule;

public interface IScheduleService {
    List<Schedule> findSchedulesByDate(LocalDate date, UUID specialistId);

    List<Schedule> findSchedulesBySpecialist(UUID specialistId);

}
