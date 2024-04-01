package com.petometry.currencyservice.service;

import com.petometry.currencyservice.rest.model.BalanceDto;
import com.petometry.currencyservice.rest.model.Transaction;

public interface TransactionService {
    BalanceDto createTransaction(String userId, Transaction transaction);
}
