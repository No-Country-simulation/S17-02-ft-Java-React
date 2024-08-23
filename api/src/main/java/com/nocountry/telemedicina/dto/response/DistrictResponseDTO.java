package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record DistrictResponseDTO(Long districtId,String districtName) {

}
