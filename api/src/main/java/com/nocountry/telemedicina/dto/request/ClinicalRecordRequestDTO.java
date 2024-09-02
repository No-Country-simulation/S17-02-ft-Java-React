package com.nocountry.telemedicina.dto.request;

import com.nocountry.telemedicina.dto.response.BookingResponseDTO;
import com.nocountry.telemedicina.dto.response.ClinicalHistoryResponseDTO;
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
public class ClinicalRecordRequestDTO {

    @NotBlank
    @Min(5)
    String visitResolution;

    String medicines;

    @NotBlank
    BookingResponseDTO booking;

    @NotBlank
    ClinicalHistoryResponseDTO clinicalHistory;
}
