package com.nocountry.telemedicina.dto.response;

import java.util.UUID;

public record SubsidiaryResponseDTO(
        UUID subsidiaryId,
        String address,
        String districtName,
        UUID clinicId,
        String clinicName) {
}
