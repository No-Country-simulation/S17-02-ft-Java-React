package com.nocountry.telemedicina.models;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

=======
import com.nocountry.telemedicina.models.enums.Week;
>>>>>>> 6154bf61d961c33b2735e02f4a831d9e1979bae0
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
@Table(name = "schedules")
public class Schedules extends Auditable{
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
    @Column(name = "schedules_repeat", nullable = false)
    private Boolean schedulesRepeat;

    @ElementCollection
    @CollectionTable(name = "weeks", joinColumns = @JoinColumn(name = "week_day"))
    @Column(name = "week")
    private List<Week>week;

    @ManyToOne
    @JoinColumn(name = "specialist_id",foreignKey = @ForeignKey(name = "FK_SCHEDULES_SPECIALIST"), nullable = false)
    private Specialist specialist;

    @OneToMany(mappedBy = "schedules",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<Booking> bookings;
}
