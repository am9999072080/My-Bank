package com.am.MyBank.service.impl;

import com.am.MyBank.credit.CreditGold;
import com.am.MyBank.model.BankCard;
import com.am.MyBank.model.Card;
import com.am.MyBank.model.User;
import com.am.MyBank.repository.CreditRepository;

import com.am.MyBank.service.CreditService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository repository;

    private final UserServiceImpl userService;


    @Override
    public Card addBalance(double amount, Authentication authentication) {
        User user = userService.getUserAut(authentication);
        CreditGold creditGold = new CreditGold(user.getCard());
        creditGold.addBalance(amount);
        user.getCard().setAllBalance(creditGold.checkAllBalance());
        return repository.save(user.getCard());
    }

    @Override
    public Card pay(double amount, Authentication authentication) {
        User user = userService.getUserAut(authentication);
        BankCard creditGold = new CreditGold(user.getCard());
        if (user.getCard().getBalance() + user.getCard().getCreditBalance() < amount) {
            return null;
        } else {
            creditGold.pay(amount);
            return repository.save(user.getCard());
        }
    }

    @Override
    public void sendByPhone(double amount, String phoneNumber, Authentication authentication) {
        User user = userService.getByPhoneNumber(phoneNumber);
        BankCard creditGold = new CreditGold(user.getCard());
        creditGold.addBalance(amount);
        user.getCard().setAllBalance(creditGold.checkAllBalance());
        repository.save(user.getCard());

    }

    @Override
    public List<Card> getAll() {
        return repository.findAll();
    }
}