package com.nocountry.telemedicina.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedules_id")
    private Long schedulesId;

    private LocalDate schedulesDay;

    private Integer schedulesDuration;

    private LocalTime schedulesStart;
    private LocalTime schedulesEnd;

    private Integer schedulesRest;

    private Boolean schedulesRepeat;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    Specialist specialist;

    private Boolean active;
}
