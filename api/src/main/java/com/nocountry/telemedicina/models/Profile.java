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
    @Column(name = "profile_name", length = 50,nullable = false)    
    private String profileName;
    @Column(name = "profile_lastname",length = 80,nullable = false)
    private String profileLastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type",nullable = false)
    private DocumentType documentType;

    @Column(name = "document_number",length = 15,nullable = false)
    private String documentNumber;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address",length = 120)
    private String address;

    @ManyToOne
    @JoinColumn(name = "district_id",foreignKey = @ForeignKey(name = "FK_PROFILES_DISTRICT"), nullable = false)
    private District district;
}