package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.DepartmentResponseDTO;

public record CityRequestDTO(String cityName, DepartmentResponseDTO department) {
}
