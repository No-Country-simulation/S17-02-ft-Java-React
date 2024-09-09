package com.nocountry.telemedicina.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.telemedicina.models.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID bookingId = UUID.randomUUID();
    @Column(name = "booking_reason",nullable = false,length = 60)
    private String bookingReason;

    @OneToOne
    @JoinColumn(name = "schedules_id",foreignKey = @ForeignKey(name = "FK_BOOKINGS_SCHEDULES"), nullable = false)
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_BOOKINGS_USER"), nullable = false)
    private User user;  

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @OneToOne(mappedBy = "booking",cascade = {CascadeType.ALL},orphanRemoval = true)
    @JsonIgnore
    private Pay pay;

    @OneToOne(mappedBy = "booking",cascade = {CascadeType.ALL},orphanRemoval = true)
    @JsonIgnore
    private ClinicalRecord clinicalRecord;
}
