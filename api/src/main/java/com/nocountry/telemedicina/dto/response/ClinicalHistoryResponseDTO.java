package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public record ClinicalHistoryResponseDTO(UUID clinicalHistoryId,UserResponseDTO user,String historyCode) {
}
