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
@Table(name = "subsidiaries")
public class Subsidiary extends Auditable {

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
