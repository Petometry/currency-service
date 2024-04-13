package com.petometry.currencyservice.service;

import com.petometry.currencyservice.rest.model.PetFoodBalancesDto;

public interface PetfoodService {
    PetFoodBalancesDto getPetFoods(String userId);

    PetFoodBalancesDto updatePetFoodbalances(String userId, PetFoodBalancesDto petfoods);
}
