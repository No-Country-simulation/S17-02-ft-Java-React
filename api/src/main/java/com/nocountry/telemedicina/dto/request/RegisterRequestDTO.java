package com.nocountry.telemedicina.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.UUID;

@Getter
public class RegisterRequestDTO {
    @NotBlank
    @UniqueElements
    private String username;

    @NotBlank
    private String password;


    @NotBlank
    private List<UUID> rolesId;
}
