package com.nocountry.telemedicina.dto.response;

import com.nocountry.telemedicina.models.enums.DocumentType;
import lombok.Getter;
import lombok.Setter;

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
        String districtName,
        String email,
        String userId ) {

}
