package com.nocountry.telemedicina.dto.response;

import com.nocountry.telemedicina.models.enums.State;

import java.util.UUID;

public record PayResponseDTO(
        UUID payId,
        String operationNumber,
        State payState,
        String clinicName,
        Double mountPay) {
}