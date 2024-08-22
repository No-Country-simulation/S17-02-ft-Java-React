package com.nocountry.telemedicina.models;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Departaments")
public class Departament {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departament_id")
    private Long departamentId;

    @Column(name = "departament_name",length = 25,nullable = false)
    private String departamentName;

    @ManyToOne
    @JoinColumn (name = "country_id",foreignKey = @ForeignKey(name = "FK_DEPARMENTS_COUNTRY"), nullable = false)
    private Country country;
}
