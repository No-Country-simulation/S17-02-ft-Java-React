package com.nocountry.telemedicina.models;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialists")
public class Specialist extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "specialist_id")
    private UUID specialistId = UUID.randomUUID();

    @Column(name = "specialist_code", nullable = false, length = 20)
    private String specialistCode;

    @ManyToOne
    @JoinColumn(name = "specialty_id", foreignKey = @ForeignKey(name = "FK_SPECIALISTS_SPECIALTY"), nullable = false)
    private Specialty specialty;


    @Column(name = "booking_price", nullable = false)
    private Double bookingPrice;

<<<<<<< HEAD
    @ManyToOne
    @JoinColumn(name = "clinic_id", foreignKey = @ForeignKey(name = "FK_SPECIALISTS_CLINIC"), nullable = false)
    private Clinic clinic;
=======
    @Column(name = "reputation", nullable = false)
    private Integer reputation = 0;

    @OneToOne
    @JoinColumn(name = "profile_id",foreignKey = @ForeignKey(name = "FK_SPECIALISTS_Profile"),nullable = false)
    private Profile profile;
>>>>>>> 5e5f8487634c5773f91a66eeb5e90c92024b3338

    @OneToMany(mappedBy = "specialist", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnore
    private List<Schedules>schedules;
}
