package com.petometry.currencyservice.rest;

import com.frameboter.rest.AbstractResource;
import com.petometry.currencyservice.rest.model.GeocoinBalance;
import com.petometry.currencyservice.service.GeocoinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/geocoins")
public class GeocoinResource extends AbstractResource {

    private final GeocoinService geocoinService;

    // @formatter:off
    @Operation(summary = "Returns balances ", description = "Returns the balances of the currently logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Balances retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content)
    })
    @GetMapping()
    GeocoinBalance getGeocoins(@AuthenticationPrincipal Jwt jwt) {
        // @formatter:on
        String userId = getUserId(jwt);
        log.info("getBalances started for userId=" + userId);
        GeocoinBalance balance = geocoinService.getGeocoinBalance(userId);
        log.info("getBalances finished for userId={} balance={}", getUserId(jwt), balance);
        return balance;
    }
}
