package com.nocountry.telemedicina.dto.response;

import java.util.UUID;

public record ReviewResponseDTO(
        UUID reviewId,
        Integer stars,
        String comment,
        UUID userId,
        String userName,
        UUID specialistId,
        String specialistName) {
}
