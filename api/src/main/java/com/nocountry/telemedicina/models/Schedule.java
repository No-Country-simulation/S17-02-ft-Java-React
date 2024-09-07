package com.nocountry.telemedicina.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public class Schedule extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "schedule_id",nullable = false)
    private UUID scheduleId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "start_time",nullable = false)
    private Integer startTime;
    @Column(name = "end_time",nullable = false)
    private Integer endTime;

    @ManyToOne
    @JoinColumn(name = "specialist_id", foreignKey = @ForeignKey(name = "FK_SCHEDULE_SPECIALIST"), nullable = false)
    private Specialist specialist;

}
