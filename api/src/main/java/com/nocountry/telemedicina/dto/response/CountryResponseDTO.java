package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public record CountryResponseDTO(Long countryId, String countryName, List<DepartmentResponseDTO>departments) {
}
