package com.nocountry.telemedicina.models;

import com.nocountry.telemedicina.models.enums.DocumentType;
import jakarta.persistence.*;
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
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "profile_id")
    private UUID profileId=UUID.randomUUID();

    private String profileName;

    private String profileLastName;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String documentNumber;

    private String avatarUrl;

    private LocalDate birth;

    private String address;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;
}
