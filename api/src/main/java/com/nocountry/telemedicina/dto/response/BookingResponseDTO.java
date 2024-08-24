package com.nocountry.telemedicina.dto.response;


import com.nocountry.telemedicina.models.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public record BookingResponseDTO(UUID bookingId, State state, SchedulesResponseDTO schedules, UserResponseDTO user, PayResponseDTO pay, ClinicalRecordResponseDTO clinicalRecord) {
}
