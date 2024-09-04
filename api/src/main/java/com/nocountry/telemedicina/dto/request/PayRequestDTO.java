package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.BookingResponseDTO;
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
public class PayRequestDTO {
    @NotBlank
    @Min(4)
    private String operationNumber;

    @NotBlank
    private BookingResponseDTO booking;
}
