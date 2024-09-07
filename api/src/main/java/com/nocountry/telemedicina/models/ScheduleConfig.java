package com.nocountry.telemedicina.models;

import com.nocountry.telemedicina.models.enums.EnumDay;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schedules_config")
public class ScheduleConfig extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedules_id", nullable = false)
    private Long schedulesConfigId;
    @Column(name = "schedules_day", nullable = false)
    private LocalDate schedulesDay;
    @Column(name = "schedules_day_end")
    private LocalDate schedulesDayEnd;
    @Column(name = "schedules_duration", nullable = false)
    private Integer schedulesDuration;
    @Column(name = "schedules_start", nullable = false)
    private LocalTime schedulesStart;
    @Column(name = "schedules_end", nullable = false)   
    private LocalTime schedulesEnd;
    @Column(name = "schedules_rest", nullable = false)
    private Integer schedulesRest;

    @Column(name = "days")
    private String days;
    @ManyToOne
    @JoinColumn(name = "specialist_id",foreignKey = @ForeignKey(name = "FK_SCHEDULES_SPECIALIST"), nullable = false)
    private Specialist specialist;

    @OneToMany(mappedBy = "schedules",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<Booking> bookings;

    public List<EnumDay> getDays() {
        if (this.days == null || this.days.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(this.days.split(","))
                .map(day -> {
                    try {
                        return EnumDay.valueOf(day);
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
