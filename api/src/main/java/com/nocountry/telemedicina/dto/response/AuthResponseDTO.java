package com.nocountry.telemedicina.dto.response;

import org.springframework.security.core.userdetails.UserDetails;

public record AuthResponseDTO(UserResponseDTO userResponseDTO, String token) {

}
