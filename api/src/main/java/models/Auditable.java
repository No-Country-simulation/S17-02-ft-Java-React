package models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Auditable {
    private UUID createBy;

    private LocalDate createdAt;

    private UUID updateBy;

    private LocalDate updatedAt;

    private UUID deleteBy;

    private LocalDate deletedAt;

    private Boolean active;
}
