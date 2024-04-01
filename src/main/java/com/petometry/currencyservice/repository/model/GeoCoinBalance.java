package com.petometry.currencyservice.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class GeoCoinBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "balance", nullable = false)
    private Long balance;

    @Column(name = "owner_id", nullable = false, unique = true)
    private String ownerId;

}
