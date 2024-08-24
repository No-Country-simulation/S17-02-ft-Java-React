package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record DepartmentResponseDTO(Long departmentId, String departmentName, List<CityResponseDTO> cities) {
}
