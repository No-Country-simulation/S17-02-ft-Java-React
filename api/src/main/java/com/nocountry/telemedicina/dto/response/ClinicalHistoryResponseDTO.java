package com.nocountry.telemedicina.dto.response;

import java.util.UUID;

public record ClinicalHistoryResponseDTO(UUID clinicalHistoryId,UserResponseDTO user,String historyCode) {
}
