package com.petometry.currencyservice.service;

import com.petometry.currencyservice.rest.model.BalancesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final GeocoinService geocoinService;

    @Override
    public BalancesDto getAllBalances(String userId) {

        BalancesDto balancesDto = new BalancesDto();
        balancesDto.setGeocoins(geocoinService.getBalance(userId).getBalance());
        return balancesDto;
    }

}
