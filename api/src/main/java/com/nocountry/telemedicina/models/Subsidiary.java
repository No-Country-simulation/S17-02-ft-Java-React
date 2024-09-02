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
@Table(name = "subsidiaries")
public class Subsidiary extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "subsidiary_id")
    private UUID subsidiaryId=UUID.randomUUID();

    @Column(name = "address",nullable = false,length = 120)
    private String address;

    @ManyToOne
    @JoinColumn(name = "district_id",foreignKey = @ForeignKey(name = "FK_SUBSIDIARIES_DISTRICT"), nullable = false)
    private District district;
    
    @ManyToOne
    @JoinColumn(name = "clinic_id",foreignKey = @ForeignKey(name = "FK_SUBSIDIARIES_CLINIC"), nullable = false)
    private Clinic clinic;
}
