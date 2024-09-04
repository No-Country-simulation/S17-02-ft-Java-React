package com.nocountry.telemedicina.dto.response;

import java.util.UUID;

public record RoleResponseDTO(
        UUID roleId,
        String roleName,
        String roleDescription) {

}
