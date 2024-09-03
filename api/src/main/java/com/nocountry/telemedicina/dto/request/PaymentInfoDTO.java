package com.nocountry.telemedicina.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentInfoDTO {

    @JsonProperty("payment_method_id")
    private String paymentMethodId;

    @JsonProperty("transaction_details")
    private TransactionDetails transactionDetails;

    @JsonProperty("additional_info")
    private AdditionalInfo additionalInfo;

    @JsonProperty("status")
    private String status;
    // Getters and setters...

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TransactionDetails {
        @JsonProperty("total_paid_amount")
        private double totalPaidAmount;

        // Getters and setters...
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AdditionalInfo {
        @JsonProperty("items")
        private List<Item> items;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        @JsonProperty("id")
        private String id;

        @JsonProperty("category_id")
        private String categoryId;

        @JsonProperty("quantity")
        private int quantity;

        @JsonProperty("title")
        private String title;

        @JsonProperty("unit_price")
        private double unitPrice;

        // Getters and setters...
    }
}
