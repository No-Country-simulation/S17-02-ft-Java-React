package com.nocountry.telemedicina.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "countries")
<<<<<<< HEAD
public class Country {
=======
public class Country extends Auditable{
>>>>>>> 6154bf61d961c33b2735e02f4a831d9e1979bae0
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
