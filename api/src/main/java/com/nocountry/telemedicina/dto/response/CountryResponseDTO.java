package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public record CountryResponseDTO(Long countryId, String countryName, List<DepartmentResponseDTO>departments) {
}
