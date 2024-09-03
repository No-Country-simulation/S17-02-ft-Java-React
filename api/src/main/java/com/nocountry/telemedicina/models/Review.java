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
@Table(name = "reviews")
public class Review extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id")
    private UUID reviewId = UUID.randomUUID();

    @Column(name = "stars",nullable = false)
    private Integer stars;

    @Column(name = "comment",nullable = false,length = 200)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "specialist_id",foreignKey = @ForeignKey(name = "FK_REVIEWS_SPECIALIST"), nullable = false)
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_REVIEWS_USER"), nullable = false)
    private User user;
}
