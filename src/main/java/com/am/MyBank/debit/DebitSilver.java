package com.am.MyBank.debit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class DebitSilver extends DebBronze {
    private final double potentialCash = 0.05;
    private final double bigAmount = 5000;

    public DebitSilver(Card card) {
        super(card);
    }

    @Override
    public boolean pay(double amount) {
        super.pay(amount);
        double d;
        if (amount >= 5000) {
            d = amount * potentialCash;
            System.out.print(", Потенциал кешбэк: " + d);
            getCard().setCashBack(getCard().getCashBack() + d);
        }
        System.out.print(", Кешбэк всего: " + getCard().getCashBack());
        return true;
    }

    @Override
    public String checkAllBalance() {
        return "\nDEBIT BALANCE: " + String.format("%.2f", getCard().getBalance()) + " CASHBACK " + String.format("%.2f", getCard().getCashBack());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DebitSilver that = (DebitSilver) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), potentialCash, bigAmount);
    }
}
