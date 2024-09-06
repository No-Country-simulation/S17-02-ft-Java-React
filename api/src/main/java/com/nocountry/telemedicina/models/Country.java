package com.nocountry.telemedicina.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long CountryId;

    @Column(name = "country_name",length = 25,nullable = false)
    private String countryName;

    @OneToMany(mappedBy = "country", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private List<Department> departments;
}
