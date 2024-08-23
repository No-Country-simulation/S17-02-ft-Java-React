package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public record SpecialtyResponseDTO(Long specialtyId, String specialtyName, String specialtyDescription, List<SpecialistResponseDTO> specialists) {
}
