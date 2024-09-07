package com.nocountry.telemedicina.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Getter
public class RegisterRequestDTO {
    @NotBlank
    @Email
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotEmpty
    private List<UUID> rolesId;
}
