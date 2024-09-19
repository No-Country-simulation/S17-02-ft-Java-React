package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.ProfileResponseDTO;
import com.nocountry.telemedicina.dto.response.SpecialtyResponseDTO;
import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistRequestDTO {

    @NotBlank
    @Min(6)
    String specialistCode;

    @NotBlank
    SpecialtyResponseDTO specialty;

    @NotBlank
    @Min(2)
    Double bookingPrice;

    @Min(0)
    @Max(5)
    Integer reputation;

    ProfileResponseDTO profile;
}
