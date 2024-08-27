package com.nocountry.telemedicina.models;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Auditable {
    private UUID createBy;

    private LocalDateTime createdAt;

    private UUID updateBy;

    private LocalDateTime updatedAt;

    private UUID deleteBy;

    private LocalDateTime deletedAt;

    private Boolean active = true;
}
