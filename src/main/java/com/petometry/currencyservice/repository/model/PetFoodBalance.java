package com.petometry.currencyservice.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class PetFoodBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "owner_id", nullable = false, unique = true)
    private String ownerId;

    @Column(name = "circle")
    private Double circle;

    @Column(name = "triangle")
    private Double triangle;

    @Column(name = "rectangle")
    private Double rectangle;

}
