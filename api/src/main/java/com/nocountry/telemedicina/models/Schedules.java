package com.nocountry.telemedicina.models;

import com.nocountry.telemedicina.models.enums.EnumDay;
import com.nocountry.telemedicina.models.enums.Week;
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
@Table(name = "schedules")
public class Schedules extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedules_id", nullable = false)
    private Long schedulesId;
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
