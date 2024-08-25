package com.nocountry.telemedicina.dto.response;


import com.nocountry.telemedicina.models.enums.State;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record BookingResponseDTO(
        UUID bookingId,
        State state,
        LocalDate schedulesDay,
        LocalTime schedulesStart,
        UUID userId,
        String username,
        Double payAmount) {
}
