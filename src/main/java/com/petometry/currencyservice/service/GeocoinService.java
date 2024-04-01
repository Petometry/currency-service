package com.petometry.currencyservice.service;

import com.petometry.currencyservice.repository.model.GeoCoinBalance;
import com.petometry.currencyservice.rest.model.BalanceDto;
import com.petometry.currencyservice.rest.model.Transaction;

public interface GeocoinService {
    GeoCoinBalance getBalance(String userId);

    BalanceDto createTransaction(String userId, Transaction transaction);
}
