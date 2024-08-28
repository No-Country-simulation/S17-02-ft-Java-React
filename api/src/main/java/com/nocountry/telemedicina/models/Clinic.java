package com.nocountry.telemedicina.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinics")
public class Clinic extends Auditable {
     @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "clinic_id")
    private UUID clinicId=UUID.randomUUID();
    
    @Column(name = "clinic_name",nullable = false,length = 70)
    private String clinicName;

    @ManyToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_CLINICS_USER"), nullable = false)
    User user;

    @OneToMany(mappedBy = "clinic",cascade = {CascadeType.ALL},orphanRemoval = true)
    @JsonIgnore
    private List<Specialist>specialists;

    @OneToMany(mappedBy = "clinic",cascade = {CascadeType.ALL},orphanRemoval = true)
    @JsonIgnore
    private List<Subsidiary> subsidiaries;
}
