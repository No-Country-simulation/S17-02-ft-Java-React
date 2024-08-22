package com.nocountry.telemedicina.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
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
@Table(name = "payments")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pay_id")
    private UUID payId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "schedules_id",foreignKey = @ForeignKey(name = "FK_PAYMENTS_SCHEDULES"),nullable = false)
    private Schedules schedules;
}
