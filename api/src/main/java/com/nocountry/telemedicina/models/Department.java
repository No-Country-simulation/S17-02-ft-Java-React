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
@Table(name = "departments")
public class Department extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name", length = 70, nullable = false)
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "FK_DEPARTMENTS_COUNTRY"), nullable = false)
    private Country country;

    @OneToMany(mappedBy = "department", cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JsonIgnore
    private List<City> cities;
}
