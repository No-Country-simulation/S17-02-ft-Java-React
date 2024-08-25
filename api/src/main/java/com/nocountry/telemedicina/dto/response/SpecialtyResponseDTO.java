package com.nocountry.telemedicina.dto.response;

import java.util.List;

public record SpecialtyResponseDTO(
        Long specialtyId,
        String specialtyName,
        String specialtyDescription,
        List<SpecialistResponseDTO> specialists) {

}
