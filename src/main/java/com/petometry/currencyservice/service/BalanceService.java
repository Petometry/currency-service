package com.petometry.currencyservice.service;

import com.petometry.currencyservice.rest.model.BalancesDto;

public interface BalanceService {
    BalancesDto getAllBalances(String userId);

}
