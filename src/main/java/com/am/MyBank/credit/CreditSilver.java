package com.am.MyBank.credit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CreditSilver extends CreditBronze {
    private final double potentialCash = 0.05;
    private final double maxAmount = 5000;

    public CreditSilver(Card card) throws RuntimeException {
        super(card);
    }


    @Override
    public boolean pay(double amount) {
        super.pay(amount);
        double d;
        if (amount >= 5000) {
            d = amount * potentialCash;
            System.out.print(", Потенциал кешбэк: " + d);
        }
        return true;
    }

    @Override
    public String checkAllBalance() {
        return super.checkAllBalance() + " CASHBACK ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), potentialCash, maxAmount);
    }
}

