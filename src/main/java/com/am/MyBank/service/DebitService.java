package com.am.MyBank.service;

import com.am.MyBank.model.Card;

public interface DebitService {
    Card addBalance(double amount);

    Card pay(double amount);

    String getAllBalance();
}
