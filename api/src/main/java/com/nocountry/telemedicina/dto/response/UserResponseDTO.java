package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

public record UserResponseDTO(UUID userId, String username, String password,List<RoleResponseDTO> role ) {

}
