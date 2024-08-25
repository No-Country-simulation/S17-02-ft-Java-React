package com.nocountry.telemedicina.dto.response;

import java.time.LocalTime;
import java.util.UUID;

public record ClinicalRecordResponseDTO(
        UUID clinicalRecordId,
        String visitResolution,
        String medicines,
        BookingResponseDTO booking,
        UUID clinicalHistoryId,
        String historyCode) {
}
