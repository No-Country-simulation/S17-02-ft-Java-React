package com.nocountry.telemedicina.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "schedules")
public class Schedule extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "schedule_id", nullable = false)
    private UUID scheduleId;

    @ManyToOne
    @JoinColumn(name = "schedule_config_id", foreignKey = @ForeignKey(name = "FK_SCHEDULE_SCHEDULE_CONFIG"), nullable = false)
    private ScheduleConfig scheduleConfig;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "specialist_id", foreignKey = @ForeignKey(name = "FK_SCHEDULE_SPECIALIST"), nullable = false)
    private Specialist specialist;

    @OneToMany(mappedBy = "schedule", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Booking> bookings;
}
