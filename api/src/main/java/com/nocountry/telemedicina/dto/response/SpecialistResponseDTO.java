package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public record SpecialistResponseDTO(UUID specialistId, String specialistCode, SpecialtyResponseDTO specialty, Double bookingPrice, ClinicResponseDTO clinic, List<SchedulesResponseDTO> schedules) {
}
