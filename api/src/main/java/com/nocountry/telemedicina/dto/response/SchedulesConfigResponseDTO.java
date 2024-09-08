package com.nocountry.telemedicina.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record SchedulesConfigResponseDTO(
        Long schedulesConfigId,
        LocalDate schedulesDayStart,
        LocalDate schedulesDayEnd,
        LocalTime schedulesStart,
        LocalTime schedulesEnd,
        LocalTime schedulesStartRest,
        LocalTime schedulesEndRest,
        Integer schedulesDuration,
        Integer schedulesRest,
        UUID specialistId,
        String specialistName) {
}
