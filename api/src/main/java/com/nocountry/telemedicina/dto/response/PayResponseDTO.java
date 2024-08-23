package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public record PayResponseDTO(UUID payId, String operationNumber, BookingResponseDTO booking) {
}
