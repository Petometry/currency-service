package com.petometry.currencyservice.service;

import com.petometry.currencyservice.repository.PetFoodBalanceRepository;
import com.petometry.currencyservice.repository.model.PetFoodBalance;
import com.petometry.currencyservice.rest.model.PetFoodBalancesDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetfoodServiceImpl implements PetfoodService {

    private final PetFoodBalanceRepository petFoodBalanceRepository;

    private final ModelMapper modelMapper;

    @Override
    public PetFoodBalancesDto getPetFoods(String userId) {

        PetFoodBalance petFoodBalance = getPetFoodBalance(userId);
        return modelMapper.map(petFoodBalance, PetFoodBalancesDto.class);
    }

    @Override
    public PetFoodBalancesDto updatePetFoodbalances(String userId, PetFoodBalancesDto petfoods) {

        PetFoodBalance petFoodBalance = getPetFoodBalance(userId);
        if (petfoods.getCircle() < 0 || petfoods.getTriangle() < 0 || petfoods.getRectangle() <0){
            throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
        petFoodBalance.setCircle(petfoods.getCircle());
        petFoodBalance.setTriangle(petfoods.getTriangle());
        petFoodBalance.setRectangle(petfoods.getRectangle());
        PetFoodBalance updatedPetFoodBalance = petFoodBalanceRepository.save(petFoodBalance);
        return modelMapper.map(updatedPetFoodBalance, PetFoodBalancesDto.class);
    }

    private PetFoodBalance getPetFoodBalance(String userId) {

        Optional<PetFoodBalance> petfoodBalanceOptional = petFoodBalanceRepository.findByOwnerId(userId);
        return petfoodBalanceOptional.orElseGet(()->createPetFoodbalance(userId));
    }

    private PetFoodBalance createPetFoodbalance(String userId) {

        PetFoodBalance petFoodBalance;
        PetFoodBalance newPetFoodBalance = new PetFoodBalance();
        newPetFoodBalance.setOwnerId(userId);
        newPetFoodBalance.setCircle(0.0);
        newPetFoodBalance.setTriangle(0.0);
        newPetFoodBalance.setRectangle(0.0);
        petFoodBalance = petFoodBalanceRepository.save(newPetFoodBalance);
        return petFoodBalance;
    }
}
