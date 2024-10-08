package com.nocountry.telemedicina.dto.response;

import com.nocountry.telemedicina.models.enums.DocumentType;

import java.time.LocalDate;
import java.util.UUID;

public record ProfileResponseDTO(
        UUID profileId,
        String profileName,
        String profileLastname,
        DocumentType documentType,
        String documentNumber,
        String avatarUrl,
        LocalDate birth,
        String address,
        String cityName,
        String email,
        String userId ) {
}
