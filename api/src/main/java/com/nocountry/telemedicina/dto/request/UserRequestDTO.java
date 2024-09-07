package com.nocountry.telemedicina.dto.request;
import com.nocountry.telemedicina.dto.response.RoleResponseDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank
    @UniqueElements
    @Email
    private String username;

    private String password;

    @NotBlank
    private List<RoleResponseDTO> roles;
}
