package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public record SchedulesResponseDTO(Long schedulesId,LocalDate schedulesDay,Integer schedulesDuration, LocalTime schedulesStart,LocalTime schedulesEnd, Integer schedulesRest,Boolean schedulesRepeat,SpecialistResponseDTO specialist) {
}