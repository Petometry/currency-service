package com.petometry.currencyservice.rest.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BalanceDto {

    private CurrencyType currency;

    private Long balance;
}
