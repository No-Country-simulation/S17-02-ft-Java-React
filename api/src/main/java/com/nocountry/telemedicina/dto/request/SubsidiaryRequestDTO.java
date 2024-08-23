package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.ClinicResponseDTO;
import com.nocountry.telemedicina.dto.response.DistrictResponseDTO;

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
public class SubsidiaryRequestDTO {

    @NotBlank
    @Min(5)
    private String address;

    @NotBlank
    private DistrictResponseDTO district;

    @NotBlank
    private ClinicResponseDTO clinic;
}
