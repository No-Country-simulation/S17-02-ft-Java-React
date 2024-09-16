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
<<<<<<< HEAD
public class Department {
    
=======
public class Department extends Auditable{

>>>>>>> 6154bf61d961c33b2735e02f4a831d9e1979bae0
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
