package com.nocountry.telemedicina.dto.response;

import java.util.List;
import java.util.UUID;

public record ClinicResponseDTO(UUID clinicId,String clinicName,UserResponseDTO user,List<SubsidiaryResponseDTO> subsidiaries,List<SpecialistResponseDTO> specialists) {
}
