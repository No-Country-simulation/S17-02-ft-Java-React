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
<<<<<<< HEAD
public class City {
    
=======
public class City extends Auditable{

>>>>>>> 6154bf61d961c33b2735e02f4a831d9e1979bae0
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name", length = 70, nullable = false)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "department_id", foreignKey = @ForeignKey(name = "FK_CITIES_DEPARTMENT"), nullable = false)
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Profile> profiles;
}
