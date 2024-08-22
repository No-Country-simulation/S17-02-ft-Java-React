package com.nocountry.telemedicina.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinical_records")
public class ClinicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "clinical_record_id")
    private UUID clinicalRecordId = UUID.randomUUID();

    @Column(name = "visit_resolution",nullable = false,length = 200)
    private String visitResolution;
    @Column(name = "medicines",nullable = false,length = 100)
    private String medicines;

    @ManyToOne
    @JoinColumn(name = "clinical_history_id",foreignKey = @ForeignKey(name = "FK_CLINICALRECORDS_CLINICALHISTORY"),nullable = false)
    ClinicalHistory clinicalHistory;
}
