package com.nocountry.telemedicina.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "clinical_histories" )
public class ClinicalHistory {
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
