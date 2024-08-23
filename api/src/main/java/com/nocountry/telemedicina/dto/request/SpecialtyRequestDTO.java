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
public class SpecialtyRequestDTO {

    @NotBlank
    @Min(4)
    String specialtyName;

    String specialtyDescription;
}
