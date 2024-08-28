package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.SpecialistResponseDTO;
import com.nocountry.telemedicina.dto.response.UserResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
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
public class ReviewRequestDTO {

    @NotBlank
    @Min(0)
    @Max(5)
    @Schema(example = "0 - 5",requiredMode = Schema.RequiredMode.REQUIRED)
    Integer stars;

    @NotBlank
    @Min(0)
    @Max(200)
    @Schema(example = "Nice doctor",requiredMode = Schema.RequiredMode.REQUIRED)
    String comment;

    @NotBlank
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    UserResponseDTO user;

    @NotBlank
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    SpecialistResponseDTO specialist;
}
