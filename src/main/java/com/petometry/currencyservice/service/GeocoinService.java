package com.petometry.currencyservice.service;

import com.petometry.currencyservice.rest.model.BalanceDto;
import com.petometry.currencyservice.rest.model.GeocoinBalance;
import com.petometry.currencyservice.rest.model.Transaction;

public interface GeocoinService {

    GeocoinBalance getGeocoinBalance(String userId);

    BalanceDto createTransaction(String userId, Transaction transaction);
}
