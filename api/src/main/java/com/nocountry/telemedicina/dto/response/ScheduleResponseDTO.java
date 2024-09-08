package com.nocountry.telemedicina.dto.response;

import com.nocountry.telemedicina.models.Specialist;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ScheduleResponseDTO(
        UUID scheduleId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        Specialist specialist)
{
}
