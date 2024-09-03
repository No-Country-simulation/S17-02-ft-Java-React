package com.nocountry.telemedicina.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

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
    private UUID id; // id de la reserva pendiente hasta el momento
    private String title; // RESERVA + NOMBRE DE LA ESPECIALIDAD
    private String description; // veremos
    private String categoryId; // SPECIALTY
    private String currencyId; // 'ARS'
    private BigDecimal unitPrice; // PRICE
}
