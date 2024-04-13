package com.petometry.currencyservice.rest;

import com.frameboter.rest.AbstractResource;
import com.petometry.currencyservice.rest.model.PetFoodBalancesDto;
import com.petometry.currencyservice.service.PetfoodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/petfoods")
public class PetfoodResource extends AbstractResource {

    private final PetfoodService petfoodService;

    // @formatter:off
    @Operation(summary = "Returns Petfood balances", description = "Returns the petfood balances of the currently logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balances retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content)
    })
    @GetMapping()
    PetFoodBalancesDto getPetfoodBalances(@AuthenticationPrincipal Jwt jwt) {
        // @formatter:on
        String userId = getUserId(jwt);
        log.info("getBalances started for userId=" + userId);
        PetFoodBalancesDto balances = petfoodService.getPetFoods(userId);
        log.info("getBalances finished for userId={} balances={}", getUserId(jwt), balances);
        return balances;
    }

    // @formatter:off
    @Operation(summary = "Updates Petfoods", description = "Updates the users current petfood balances")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balances added successfully"),
            @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content),
            @ApiResponse(responseCode = "400", description = "Values can not be 0", content = @Content)
    })
    @PutMapping()
    PetFoodBalancesDto updatePetFoodBalances(@AuthenticationPrincipal Jwt jwt,@RequestBody @Valid PetFoodBalancesDto petfoods) {
        // @formatter:on
        String userId = getUserId(jwt);
        log.info("getBalances started for userId=" + userId);
        PetFoodBalancesDto balances = petfoodService.updatePetFoodbalances(userId, petfoods);
        log.info("getBalances finished for userId={} balances={}", getUserId(jwt), balances);
        return balances;
    }

}
