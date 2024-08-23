package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.DepartmentResponseDTO;
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
public class CityRequestDTO {

    @Min(3)
    @Max(15)
    @NotBlank
    private String cityName;

    @NotBlank
    private DepartmentResponseDTO department;
}
