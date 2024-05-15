package com.am.MyBank.credit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CreditCash extends CreditCard {
    private final double bonusForBuy = 0.01;
    private double bonus;

    public CreditCash(Card card) throws RuntimeException {
        super(card);
    }

    public CreditCash(Card card, double bonus) throws RuntimeException {
        super(card);
        this.bonus = bonus;
    }

    @Override
    public boolean pay(double amount) {
        double d;
        if (super.pay(amount)) {
            d = amount * bonusForBuy;
            bonus += d;
            System.out.print(", Базовый кешбэк: " + d);
        } else {
            return super.pay(amount);
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditCash that = (CreditCash) o;
        return Double.compare(bonusForBuy, that.bonusForBuy) == 0 && Double.compare(bonus, that.bonus) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bonusForBuy, bonus);
    }
}
