package com.nocountry.telemedicina.models;

import java.util.UUID;

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
@Table(name = "payments")
public class Pay extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pay_id")
    private UUID payId = UUID.randomUUID();

    @Column(name = "operationNumber",length = 18,nullable = false)
    private String operationNumber;

    @OneToOne
    @JoinColumn(name = "booking_id",foreignKey = @ForeignKey(name = "FK_PAYMENTS_BOOKING"),nullable = false)
    private Booking booking;
}
