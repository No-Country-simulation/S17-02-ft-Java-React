package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public record DepartmentResponseDTO(Long departmentId, String departmentName, List<CityResponseDTO> cities) {
}
