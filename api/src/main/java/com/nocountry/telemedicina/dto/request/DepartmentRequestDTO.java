package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.CountryResponseDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequestDTO {

    @NotBlank
    @Min(3)
    private String departmentName;

    @NotBlank
    private CountryResponseDTO country;
}
