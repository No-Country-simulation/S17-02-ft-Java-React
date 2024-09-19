package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.ScheduleResponseDTO;
import com.nocountry.telemedicina.dto.response.SchedulesConfigResponseDTO;
import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import com.nocountry.telemedicina.models.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

    @NotNull
    private ScheduleResponseDTO schedule;

}
