package com.nocountry.telemedicina.dto.response;

import java.util.List;


public record CountryResponseDTO(
        Long countryId,
        String countryName,
        List<DepartmentResponseDTO>departments) {
}
