package com.nocountry.telemedicina.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

// esto viene desde el front
public class ItemPreferenceDTO {
    @NotBlank
    private UUID id; // id de la reserva pendiente hasta el momento
    @NotBlank
    private String title; // RESERVA + NOMBRE DE LA ESPECIALIDAD
    @NotBlank
    private String description; // veremos
    @NotBlank
    private String categoryId; // SPECIALTY
    @NotBlank
    private String currencyId; // 'ARS'
    @NotBlank
    private BigDecimal unitPrice; // PRICE
}
