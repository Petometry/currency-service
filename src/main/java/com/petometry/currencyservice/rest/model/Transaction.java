package com.petometry.currencyservice.rest.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Transaction {

    private CurrencyType currency;

    private String source;

    private String target;

    private Double value;
}
