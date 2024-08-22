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
@Table(name = "Sites")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "site_id")
    private UUID siteId=UUID.randomUUID();

    private String address;

    @ManyToOne
    @JoinColumn(name = "district_id")
    District district;
    
    @ManyToOne
    @JoinColumn(name = "clinic_id")
    Clinic clinic;
}
