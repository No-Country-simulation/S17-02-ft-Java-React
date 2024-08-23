package com.nocountry.telemedicina.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDTO {

    @NotBlank
    @Min(2)
    private String roleName;

    @Min(2)
    private String roleDescription;
}
