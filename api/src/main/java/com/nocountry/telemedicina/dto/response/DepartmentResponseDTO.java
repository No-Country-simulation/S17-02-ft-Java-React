package com.nocountry.telemedicina.dto.response;

import java.util.List;

public record DepartmentResponseDTO(Long departmentId, String departmentName, List<CityResponseDTO> cities) {
}
