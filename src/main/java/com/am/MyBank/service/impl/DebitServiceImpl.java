package com.am.MyBank.service.impl;

import com.am.MyBank.debit.DebitGold;
import com.am.MyBank.model.BankCard;
import com.am.MyBank.model.Card;
import com.am.MyBank.model.User;
import com.am.MyBank.repository.DebitRepository;
import com.am.MyBank.repository.UserRepository;
import com.am.MyBank.service.DebitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebitServiceImpl implements DebitService {
    @Autowired
    DebitRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;


    @Override
    public Card addBalance(double amount, Authentication authentication) {
        User user = userService.get(authentication);
        BankCard debitGold = new DebitGold(user.getCard());
        debitGold.addBalance(amount);

        user.getCard().setAllBalance(debitGold.checkAllBalance());
        userRepository.save(user);
        return repository.save(user.getCard());
    }

    @Override
    public Card pay(double amount, Authentication authentication) {
        User user = userService.get(authentication);
        if (user.getCard().getBalance() < amount) {
            return null;
        } else {
            BankCard bankcard = new DebitGold(user.getCard());
            bankcard.pay(amount);
            userRepository.save(user);
            return repository.save(user.getCard());
        }
    }


    @Override
    public List<Card> getAll() {
        return repository.findAll();
    }
}