package com.petometry.currencyservice.service;

import com.petometry.currencyservice.repository.GeoCoinBalanceRepository;
import com.petometry.currencyservice.repository.model.GeoCoinBalance;
import com.petometry.currencyservice.rest.model.BalanceDto;
import com.petometry.currencyservice.rest.model.CurrencyType;
import com.petometry.currencyservice.rest.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeocoinServiceImpl implements GeocoinService {

    private final GeoCoinBalanceRepository balanceRepository;

    @Override
    public GeoCoinBalance getBalance(String userId) {

        Optional<GeoCoinBalance> balanceOptional = balanceRepository.findByOwnerId(userId);
        GeoCoinBalance balance;
        if (balanceOptional.isEmpty()) {
            balance = new GeoCoinBalance();
            balance.setOwnerId(userId);
            balance.setBalance(10.0);
        } else {
            balance = balanceOptional.get();
        }
        return balance;
    }

    @Override
    public BalanceDto createTransaction(String userId, Transaction transaction) {
        GeoCoinBalance balance = getBalance(userId);
        if (transaction.getSource().equals("SERVER") && transaction.getTarget().equals(userId)) {
            balance.setBalance(balance.getBalance() + transaction.getValue());
        } else if (transaction.getSource().equals(userId) && transaction.getTarget().equals("SERVER")) {
            balance.setBalance(balance.getBalance() - transaction.getValue());
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403));
        }
        checkBalanceLegality(balance);
        return convertToBalanceDto(balanceRepository.save(balance));
    }

    private BalanceDto convertToBalanceDto(GeoCoinBalance balance) {
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setCurrency(CurrencyType.GEOCOIN);
        balanceDto.setBalance(balance.getBalance());
        return balanceDto;
    }

    private void checkBalanceLegality(GeoCoinBalance balance) {
        if (balance.getBalance() < 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403));
        }
    }
}
