package com.nocountry.telemedicina.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record RoleResponseDTO(Long roleId, String roleName, String roleDescription) {

}
