package com.nocountry.telemedicina.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
public class RegisterRequestDTO {
    @NotBlank
    @UniqueElements
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private List<Long> rolesId;
}
