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
@Table(name = "districts")
public class District extends Auditable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "district_name",length = 25,nullable = false)
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "city_id",foreignKey = @ForeignKey(name = "FK_DISTRICTS_CITY"), nullable = false)
    private City city;
    
    @OneToMany(mappedBy = "district",cascade = {CascadeType.ALL},orphanRemoval = true)
    @JsonIgnore
    private List<Profile>profiles;
}
