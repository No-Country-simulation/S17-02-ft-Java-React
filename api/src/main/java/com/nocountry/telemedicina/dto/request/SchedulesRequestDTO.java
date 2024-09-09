package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.config.customValidate.ValidEnumDay;
import com.nocountry.telemedicina.dto.response.SpecialistResponseDTO;
import com.nocountry.telemedicina.models.enums.EnumDay;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.PrivilegedAction;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesRequestDTO {

    @NotNull
    private LocalDate schedulesDayStart;

    @NotNull
    private LocalDate schedulesDayEnd;

    @NotNull
    private LocalTime schedulesStart;

    @NotNull
    private LocalTime schedulesEnd;

    @NotNull
    private LocalTime schedulesStartRest;

    @NotNull
    private LocalTime schedulesEndRest;

    @NotNull
    private Integer schedulesDuration;

    @NotNull
    private Integer schedulesRest;


    @ValidEnumDay
    private List<EnumDay> days;
}
