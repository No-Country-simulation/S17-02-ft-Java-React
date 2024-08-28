package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.CityResponseDTO;
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
public class DistrictRequestDTO {

    @NotBlank
    @Min(3)
    private String districtName;

    @NotBlank
    private CityResponseDTO city;
}
