package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public record ClinicalRecordResponseDTO(UUID clinicalRecordId,String visitResolution,String medicines,BookingResponseDTO booking,ClinicalHistoryResponseDTO clinicalHistory) {
}
