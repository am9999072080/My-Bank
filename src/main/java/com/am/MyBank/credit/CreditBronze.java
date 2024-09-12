package com.am.MyBank.credit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CreditBronze extends CreditCard {
    private final double cashBackForBuy = 0.01;

    public CreditBronze(Card card) throws RuntimeException {
        super(card);
    }


    @Override
    public boolean pay(double amount) {
        double d;
        d = amount * cashBackForBuy;
        getCard().setCashBack(getCard().getCashBack() + d);
        System.out.print(", Базовый кешбэк: " + d);
        return super.pay(amount);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditBronze that = (CreditBronze) o;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cashBackForBuy);
    }
}
