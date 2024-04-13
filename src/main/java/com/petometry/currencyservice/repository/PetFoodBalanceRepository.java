package com.petometry.currencyservice.repository;

import com.petometry.currencyservice.repository.model.PetFoodBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetFoodBalanceRepository extends JpaRepository<PetFoodBalance, Long> {
    Optional<PetFoodBalance> findByOwnerId(String ownerId);
}