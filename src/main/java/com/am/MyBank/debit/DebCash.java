package com.am.MyBank.debit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class DebCash extends DebitCard {
    private final double bonusForBuy = 0.01;


    public DebCash(Card card) {
        super(card);
    }

    @Override
    public boolean pay(double amount) throws NullPointerException {
        double d;
        if (super.pay(amount)) {
            if (getCard().getBalance() >= amount) {
                d = amount * bonusForBuy;
                getCard().setBonus(getCard().getBonus() + d);
                getCard().setBalance(getCard().getBalance() - amount);
                System.out.print("ПОКУПКА: " + -amount);

                System.out.print(", Базовый кешбэк: " + d);
            } else {
                throw new NullPointerException("Недостаточно средств!");
            }
        }
        return super.pay(amount);
    }

    public double getBonusForBuy() {
        return bonusForBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DebCash debCash = (DebCash) o;
        return Double.compare(bonusForBuy, debCash.bonusForBuy) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bonusForBuy);
    }
}
