package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.DistrictResponseDTO;
import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import com.nocountry.telemedicina.models.enums.DocumentType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestDTO {

    @NotBlank
    @Min(2)
    @Max(40)
    private String profileName;

    @NotBlank
    @Min(2)
    @Max(50)
    private String profileLastname;

    @NotBlank
    private DocumentType documentType;

    @NotBlank
    @Min(8)
    @Max(15)
    private String documentNumber;


    private String avatarUrl;

    private LocalDate birth;

    private String address;

    private DistrictResponseDTO district;

    @NotBlank
    private UserResponseDTO user;
}
