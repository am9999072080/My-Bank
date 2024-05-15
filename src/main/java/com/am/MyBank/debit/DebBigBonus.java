package com.am.MyBank.debit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class DebBigBonus extends DebCash {
    private final double potentialCash = 0.05;
    private final double bigAmount = 5000;

    public DebBigBonus(Card card) {
        super(card);
    }

    @Override
    public boolean pay(double amount) {
        super.pay(amount);
        double d;
        if (amount >= 5000) {
            d = amount * potentialCash;
            System.out.print(", Потенциал кешбэк: " + d);
            getCard().setBonus(getCard().getBonus() + d);
        }
        System.out.print(", Кешбэк всего: " + getCard().getBonus());
        return true;
    }

    @Override
    public String checkAllBalance() {
        return "\nDEBIT BALANCE: " + String.format("%.2f", getCard().getBalance()) + " CASHBACK " + String.format("%.2f", getCard().getBonus());
    }

    public double getPotentialCash() {
        return potentialCash;
    }

    public double getBigAmount() {
        return bigAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DebBigBonus that = (DebBigBonus) o;
        return Double.compare(potentialCash, that.potentialCash) == 0 && Double.compare(bigAmount, that.bigAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), potentialCash, bigAmount);
    }
}
