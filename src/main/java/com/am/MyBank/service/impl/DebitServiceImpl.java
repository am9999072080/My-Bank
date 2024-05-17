package com.am.MyBank.service.impl;

import com.am.MyBank.BankCard;
import com.am.MyBank.debit.DebAccumulation;
import com.am.MyBank.model.Card;
import com.am.MyBank.repository.DebitRepository;
import com.am.MyBank.service.DebitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class DebitServiceImpl implements DebitService {
    private final DebitRepository repository;
    public LocalDateTime time = LocalDateTime.now();

    private final Card card = new Card();

    private final BankCard bankcard = new DebAccumulation(card);


    public String formatTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return time.format(format);
    }

    @Override
    public Card addBalance(double amount) {
        bankcard.addBalance(amount);
        card.setBalance(bankcard.getCard().getBalance());
        card.setBonus(bankcard.getCard().getBonus());
        card.setAccumulation(bankcard.getCard().getAccumulation());
        card.setDate(formatTime());
        card.setAllBalance(bankcard.checkAllBalance());
        return repository.save(card);
    }

    @Override
    public Card pay(double amount) {
        bankcard.pay(amount);
        card.setBalance(bankcard.getCard().getBalance());
        card.setBonus(bankcard.getCard().getBonus());
        card.setAccumulation(bankcard.getCard().getAccumulation());
        card.setDate(formatTime());
        card.setAllBalance(bankcard.checkAllBalance());
        return repository.save(card);
    }

    @Override
    public String getAllBalance() {
        return bankcard.getCard().getAllBalance();
    }
}
