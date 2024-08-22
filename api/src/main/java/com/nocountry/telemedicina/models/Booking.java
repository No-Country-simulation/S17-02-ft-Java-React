package com.nocountry.telemedicina.models;

import java.util.UUID;

import com.nocountry.telemedicina.models.enums.State;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "booking_id")
    private UUID bookingId = UUID.randomUUID();

    private String bookingReason;

    @ManyToOne
    @JoinColumn(name = "schedules_id")
    private Schedules schedules;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  

    @Enumerated(EnumType.STRING)
    private Enum<State> state;
}
