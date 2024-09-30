package com.am.MyBank.service.impl;

import com.am.MyBank.debit.DebitGold;
import com.am.MyBank.model.BankCard;
import com.am.MyBank.model.Card;
import com.am.MyBank.model.User;
import com.am.MyBank.repository.DebitRepository;

import com.am.MyBank.service.DebitService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class DebitServiceImpl implements DebitService {

    private final DebitRepository repository;

    private final UserServiceImpl userService;


    @Override
    public Card addBalance(double amount, Authentication authentication) {
        User user = userService.getUserAut(authentication);
        BankCard debitGold = new DebitGold(user.getCard());
        debitGold.addBalance(amount);
        user.getCard().setAllBalance(debitGold.checkAllBalance());
        return repository.save(user.getCard());
    }

    @Override
    public Card pay(double amount, Authentication authentication) {
        User user = userService.getUserAut(authentication);
        DebitGold debitGold = new DebitGold(user.getCard());
        if (user.getCard().getBalance() < amount) {
            return null;
        } else {
            debitGold.pay(amount);
            return repository.save(user.getCard());
        }
    }

    @Override
    public void sendByPhone(double amount, String phoneNumber, Authentication authentication) {
        User user = userService.getByPhoneNumber(phoneNumber);
        BankCard debitGold = new DebitGold(user.getCard());
        debitGold.addBalance(amount);
        user.getCard().setAllBalance(debitGold.checkAllBalance());
        repository.save(user.getCard());
    }

    @Override
    public List<Card> getAll() {
        return repository.findAll();
    }
}