package com.petometry.currencyservice.service;

import com.petometry.currencyservice.rest.model.BalanceDto;
import com.petometry.currencyservice.rest.model.CurrencyType;
import com.petometry.currencyservice.rest.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final GeocoinService geocoinService;

    @Override
    public BalanceDto createTransaction(String userId, Transaction transaction) {

        if (CurrencyType.GEOCOIN.equals(transaction.getCurrency())) {
            return geocoinService.createTransaction(userId, transaction);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
    }
}
