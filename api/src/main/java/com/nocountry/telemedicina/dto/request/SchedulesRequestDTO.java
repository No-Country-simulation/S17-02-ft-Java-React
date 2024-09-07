package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.config.customValidate.ValidEnumDay;
import com.nocountry.telemedicina.dto.response.SpecialistResponseDTO;
import com.nocountry.telemedicina.models.enums.EnumDay;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesRequestDTO {

    @NotBlank
    private LocalDate schedulesDay;


    @NotBlank
    private LocalTime schedulesStart;

    @NotBlank
    private LocalTime schedulesEnd;

    @NotBlank
    private Integer schedulesDuration;

    @NotBlank
    private Integer schedulesRest;

    @NotBlank
    private SpecialistResponseDTO specialist;

    @ValidEnumDay
    private List<EnumDay> days;
}
