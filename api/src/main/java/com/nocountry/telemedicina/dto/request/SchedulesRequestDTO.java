package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.SpecialistResponseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesRequestDTO {

    @NotBlank
    LocalDate schedulesDay;

    @NotBlank
    Integer schedulesDuration;

    @NotBlank
    LocalTime schedulesStart;

    @NotBlank
    LocalTime schedulesEnd;

    @NotBlank
    Integer schedulesRest;

    @NotBlank
    Boolean schedulesRepeat;

    @NotBlank
    SpecialistResponseDTO specialist;
}
