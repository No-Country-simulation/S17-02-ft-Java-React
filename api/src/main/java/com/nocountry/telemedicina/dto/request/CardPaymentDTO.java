package com.nocountry.telemedicina.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardPaymentDTO {
    private BigDecimal transactionAmount;
    private String token;
    private Integer installments;
    private String paymentMethodId;
    private PayerCardPayment payer;

    @Getter
    @Setter
    public static class PayerCardPayment {
        private String email;
        private Identification identification;
    }

    @Setter
    @Getter
    public static class Identification {
        private String number;
        private String type;
    }

}