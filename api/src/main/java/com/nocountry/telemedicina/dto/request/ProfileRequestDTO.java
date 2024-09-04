package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.DistrictResponseDTO;
import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import com.nocountry.telemedicina.models.enums.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "Carlos",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profileName;

    @NotBlank
    @Min(2)
    @Max(50)
    @Schema(example = "Quispe",requiredMode = Schema.RequiredMode.REQUIRED)
    private String profileLastname;

    @NotBlank
    @Schema(example = "DNI",requiredMode = Schema.RequiredMode.REQUIRED)
    private DocumentType documentType;

    @NotBlank
    @Min(8)
    @Max(15)
    @Schema(example = "68941345",requiredMode = Schema.RequiredMode.REQUIRED)
    private String documentNumber;

    @Schema(example = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpj2elRrHLpPcNjxMfFdLA9zSoxiCbivEvHQ&s")
    private String avatarUrl;

    @Schema(example = "1986-05-12")
    private LocalDate birth;

    @Schema(example = "Urb. Los Pinos 234")
    private String address;

    private DistrictResponseDTO district;

    private UserResponseDTO user;
}

