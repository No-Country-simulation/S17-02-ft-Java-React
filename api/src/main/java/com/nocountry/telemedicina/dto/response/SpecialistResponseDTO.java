package com.nocountry.telemedicina.dto.response;

import java.util.List;
import java.util.UUID;

public record SpecialistResponseDTO(
        UUID specialistId,
        String specialistCode,
        UUID specialtyId,
        String specialtyName,
        Double bookingPrice,
        UUID clinicId,
        String clinicName,
        List<SchedulesResponseDTO> schedules) {
}
