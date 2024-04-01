package com.petometry.currencyservice.rest;

import com.frameboter.rest.AbstractResource;
import com.petometry.currencyservice.rest.model.BalanceDto;
import com.petometry.currencyservice.rest.model.Transaction;
import com.petometry.currencyservice.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class TransactionResource extends AbstractResource {

    private final TransactionService transactionService;

    // @formatter:off
    @Operation(summary = "Creates a transaction", description = "Creates a transaction for the given parameters and returns the new balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction created successfully"),
            @ApiResponse(responseCode = "401", description = "User is not logged in via Keycloak", content = @Content)
    })
    @PostMapping("/transactions")
    BalanceDto createTransaction(@AuthenticationPrincipal Jwt jwt, @RequestBody Transaction transaction) {
        // @formatter:on
        String userId = getUserId(jwt);
        log.info("createTransaction started for userId={} transaction={}", userId, transaction);
        BalanceDto updatedBalance = transactionService.createTransaction(userId, transaction);
        log.info("createTransaction finished for userId={} updatedBalance={}", getUserId(jwt), updatedBalance);
        return updatedBalance;
    }
}
