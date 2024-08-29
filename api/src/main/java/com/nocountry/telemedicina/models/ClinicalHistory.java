package com.nocountry.telemedicina.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinical_histories" )
public class ClinicalHistory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "clinical_history_id")
    private UUID clinicalHistoryId = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_CLINICAL_HISTORY_USER"), nullable = false)
    private User user;

    @Column(name = "history_code", nullable = false,length = 30)
    private String historyCode;
}
