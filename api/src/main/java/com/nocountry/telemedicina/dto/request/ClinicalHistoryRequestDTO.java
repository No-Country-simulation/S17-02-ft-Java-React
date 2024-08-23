package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalHistoryRequestDTO {
    private UserResponseDTO user;
    private String historyCode;
}
