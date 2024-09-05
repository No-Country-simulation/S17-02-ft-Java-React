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
@Table(name = "cities")
public class City extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name", length = 25, nullable = false)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "FK_CITIES_DEPARTMENT"), nullable = false)
    private Department department;
}
