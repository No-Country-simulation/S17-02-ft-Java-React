package com.nocountry.telemedicina.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "specialists")
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "specialist_id")
    private UUID specialistId = UUID.randomUUID();

    private String specialistCode;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    private Double bookingPrice;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;
}
