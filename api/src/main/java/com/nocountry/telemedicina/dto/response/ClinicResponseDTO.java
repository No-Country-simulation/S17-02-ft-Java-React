package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public record ClinicResponseDTO(UUID clinicId,String clinicName,UserResponseDTO user,List<SubsidiaryResponseDTO> subsidiaries,List<SpecialistResponseDTO> specialists) {
}
