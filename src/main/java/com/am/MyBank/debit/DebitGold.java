package com.am.MyBank.debit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class DebitGold extends DebitSilver {
    private final double percentAccumulation = 0.005;

    public DebitGold(Card card) {
        super(card);
    }

    @Override
    public void addBalance(double amount) throws RuntimeException {
        super.addBalance(amount);
        double d = amount * percentAccumulation;
        getCard().setBonus(getCard().getBonus() + d);
        System.out.print(", Накопление от пополнения: " + d);
        System.out.print(", Баланс накопления: " + getCard().getBonus());
    }

    @Override
    public String checkAllBalance() {
        return "ИНФОРМАЦИЯ О ПОСЛЕДНОЙ УСПЕШНОЙ АВТОРИЗАЦИИ В MyBank:" + getCard().getDate() + ":  " + super.checkAllBalance() + " BONUS: " + getCard().getBonus() + "\n";
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
        return Objects.hash(super.hashCode(), percentAccumulation);
    }
}
