package com.nocountry.telemedicina.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedules extends Auditable {
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
    private Specialist specialist;

    @OneToMany(mappedBy = "schedules",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<Booking> bookings;
}
