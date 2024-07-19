package com.am.MyBank.service;

import com.am.MyBank.model.Card;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CreditService {

    Card addBalance(double amount, Authentication authentication);

    Card pay(double amount, Authentication authentication);
    void sendByPhone(double amount, String phoneNumber, Authentication authentication);

    List<Card> getAll();
}

