package com.nocountry.telemedicina.models;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedules_id", nullable = false)
    private Long schedulesId;
    @Column(name = "schedules_day", nullable = false)
    private LocalDate schedulesDay;
    @Column(name = "schedules_duration", nullable = false)
    private Integer schedulesDuration;
    @Column(name = "schedules_start", nullable = false)
    private LocalTime schedulesStart;
    @Column(name = "schedules_end", nullable = false)   
    private LocalTime schedulesEnd;
    @Column(name = "schedules_rest", nullable = false)
    private Integer schedulesRest;
    @Column(name = "schedules_repeat", nullable = false)
    private Boolean schedulesRepeat;

    @ManyToOne
    @JoinColumn(name = "specialist_id",foreignKey = @ForeignKey(name = "FK_SCHEDULES_SPECIALIST"), nullable = false)
    Specialist specialist;

    private Boolean active;
}
