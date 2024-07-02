package com.am.MyBank.debit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class DebBronze extends DebitCard {
    private final double cashBackForBuy = 0.01;

    public DebBronze(Card card) {
        super(card);
    }

    @Override
    public boolean pay(double amount) throws NullPointerException {
        double d;
        if (getCard().getBalance() >= amount) {
            d = amount * cashBackForBuy;
            getCard().setCashBack(getCard().getCashBack() + d);
            getCard().setBalance(getCard().getBalance() - amount);
            System.out.print("ПОКУПКА: " + -amount);

            System.out.print(", Базовый кешбэк: " + d);
            return super.pay(amount);
        } else {
            throw new NullPointerException("Недостаточно средств!");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DebBronze debBronze = (DebBronze) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cashBackForBuy);
    }
}
