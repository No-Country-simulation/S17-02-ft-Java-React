package com.nocountry.telemedicina.dto.response;

import com.nocountry.telemedicina.models.enums.DocumentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public record ProfileResponseDTO(UUID profileId,String profileName, String profileLastname,DocumentType documentType, String documentNumber, String avatarUrl,LocalDate birth,String address, DistrictResponseDTO district, UserResponseDTO user ) {

}