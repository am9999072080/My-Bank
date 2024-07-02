package com.am.MyBank.debit;


import com.am.MyBank.model.BankCard;
import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DebitCard extends BankCard {
    public DebitCard(Card card) {
        super(card);
    }

    @Override
    public void addBalance(double amount) throws RuntimeException {
        if (amount <= 0) {
            throw new RuntimeException("Введенное число должен быть > 0");
        }
        getCard().setBalance(getCard().getBalance() + amount);

        System.out.print("ПОПОЛНЕНИЕ: " + amount);
    }

    @Override
    public double checkBalance() {
        return getCard().getBalance();
    }
}


