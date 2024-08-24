package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record SpecialtyResponseDTO(Long specialtyId, String specialtyName, String specialtyDescription, List<SpecialistResponseDTO> specialists) {

}
