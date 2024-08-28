package com.nocountry.telemedicina.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private UserResponseDTO userResponseDTO;
    private String token;

}
