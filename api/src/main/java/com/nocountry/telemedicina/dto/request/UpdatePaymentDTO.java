package com.nocountry.telemedicina.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentDTO {

    @NotBlank
    private String mpPaymentId;

    @NotNull
    private Boolean paymentStatus;
}
