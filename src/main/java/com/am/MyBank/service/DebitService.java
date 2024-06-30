package com.am.MyBank.service;

import com.am.MyBank.model.Card;
import org.springframework.security.core.Authentication;


import java.util.List;

public interface DebitService {
    Card addBalance(double amount, Authentication authentication);

    Card pay(double amount, Authentication authentication);

    List<Card> getAll();
}
