package com.nocountry.telemedicina.dto.response;

import java.util.UUID;

// TODO: Usar de preferencia una referencia con perfil
public record ClinicalHistoryResponseDTO(
        UUID clinicalHistoryId,
        String username,
        String userId,
        String historyCode) {
}
