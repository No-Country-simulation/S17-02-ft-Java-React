package com.nocountry.telemedicina.dto.response;

import com.nocountry.telemedicina.models.Clinic;
import com.nocountry.telemedicina.models.District;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public record SubsidiaryResponseDTO(UUID subsidiaryId,String address,District district,Clinic clinic) {
}
