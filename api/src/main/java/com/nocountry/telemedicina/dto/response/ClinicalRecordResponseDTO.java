package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public record ClinicalRecordResponseDTO(UUID clinicalRecordId,String visitResolution,String medicines,BookingResponseDTO booking,ClinicalHistoryResponseDTO clinicalHistory) {
}
