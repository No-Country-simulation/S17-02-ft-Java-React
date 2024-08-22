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
@Table(name = "specialists")
public class Specialist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "specialist_id")
    private UUID specialistId = UUID.randomUUID();

    @Column(name = "specialist_code",nullable = false,length = 20)
    private String specialistCode;

    @ManyToOne
    @JoinColumn(name = "specialty_id",foreignKey = @ForeignKey(name = "FK_SPECIALISTS_SPECIALTY"), nullable = false)
    private Specialty specialty;


    @Column(name = "booking_price",nullable = false)
    private Double bookingPrice;

    @ManyToOne
    @JoinColumn(name = "clinic_id",foreignKey = @ForeignKey(name = "FK_SPECIALISTS_CLINIC"), nullable = false)
    private Clinic clinic;
}