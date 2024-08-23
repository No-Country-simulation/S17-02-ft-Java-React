package com.nocountry.telemedicina.dto.response;


import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public record SubsidiaryResponseDTO(UUID subsidiaryId,String address,DistrictResponseDTO district,ClinicResponseDTO clinic) {
}
