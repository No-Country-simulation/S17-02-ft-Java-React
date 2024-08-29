package com.nocountry.telemedicina.dto.response;

import java.util.List;
import java.util.UUID;

public record SpecialistResponseDTO(
        UUID specialistId,
        String specialistCode,
        Long specialtyId,
        String specialtyName,
        Double bookingPrice,
        UUID clinicId,
        String clinicName,
        String specialistName,
        String specialistLastname,
        Integer reputation,
        List<SchedulesResponseDTO> schedules) {
}
