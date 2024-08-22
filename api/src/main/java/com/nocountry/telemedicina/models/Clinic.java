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
@Table(name = "clinics")
public class Clinic {
     @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "clinic_id")
    private UUID clinicId=UUID.randomUUID();
    
    @Column(name = "clinic_name",nullable = false,length = 70)
    private String clinicName;

    @ManyToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_CLINICS_USER"), nullable = false)
    User user;
}
