package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.CountryResponseDTO;

public record DepartmentRequestDTO(String departmentName, CountryResponseDTO country) {
}
