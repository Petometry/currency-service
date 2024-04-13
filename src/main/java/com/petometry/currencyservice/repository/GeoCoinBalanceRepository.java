package com.petometry.currencyservice.repository;

import com.petometry.currencyservice.repository.model.GeoCoinBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeoCoinBalanceRepository extends JpaRepository<GeoCoinBalance, Long> {
    Optional<GeoCoinBalance> findByOwnerId(String ownerId);
}