package com.nocountry.telemedicina.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.nocountry.telemedicina.dto.response.ScheduleResponseDTO;
import com.nocountry.telemedicina.models.Schedule;

public interface IScheduleService {
    List<ScheduleResponseDTO> findSchedulesByDate(LocalDate date, UUID specialistId);

    List<ScheduleResponseDTO> findSchedulesBySpecialist(UUID specialistId);

    ScheduleResponseDTO findScheduleById(UUID scheduleId);
}
