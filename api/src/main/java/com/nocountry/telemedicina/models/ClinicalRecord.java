package com.nocountry.telemedicina.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinical_records")
public class ClinicalRecord extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "clinical_record_id")
    private UUID clinicalRecordId = UUID.randomUUID();

    @Column(name = "visit_resolution",nullable = false,length = 200)
    private String visitResolution;
    @Column(name = "medicines",nullable = false,length = 100)
    private String medicines;

    @OneToOne
    @JoinColumn(name = "booking_id",foreignKey = @ForeignKey(name = "FK_CLINICAL_RECORDS_BOOKING"),nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "clinical_history_id",foreignKey = @ForeignKey(name = "FK_CLINICAL_RECORDS_CLINICAL_HISTORY"),nullable = false)
    private ClinicalHistory clinicalHistory;

}
