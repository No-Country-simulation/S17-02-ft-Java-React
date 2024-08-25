package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.UserResponseDTO;
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
public class ClinicRequestDTO {

    @NotBlank
    private UserResponseDTO user;

    @NotBlank
    @Min(5)
    private String clinicName;
}
