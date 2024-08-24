package com.nocountry.telemedicina.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record SchedulesResponseDTO(Long schedulesId,LocalDate schedulesDay,Integer schedulesDuration, LocalTime schedulesStart,LocalTime schedulesEnd, Integer schedulesRest,Boolean schedulesRepeat,SpecialistResponseDTO specialist) {
}
