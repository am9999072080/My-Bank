package com.am.MyBank.credit;

import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CreditGold extends CreditSilver {
    private final double percentAccumulation = 0.005;
    private double accumulation;

    public CreditGold(Card card) throws RuntimeException {
        super(card);
    }

    public CreditGold(Card card, double accumulation) throws RuntimeException {
        super(card);
        this.accumulation = accumulation;
    }

    @Override
    public void addBalance(double amount) {
        super.addBalance(amount);
        double d = amount * percentAccumulation;
        accumulation = accumulation + d;
        System.out.print(", Начислено бонусов: " + d);
        System.out.print(", Всего бонусов: " + accumulation);
    }

    @Override
    public String checkAllBalance() {
        return "СРЕДСТВА НА ВАШИХ СЧЕТАХ В СИСТЕМЕ MyBank НА " + getCard().getDate() + ":  " + super.checkAllBalance() + " BONUS:: " + String.format("%.2f", accumulation) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditGold that = (CreditGold) o;
        return Double.compare(percentAccumulation, that.percentAccumulation) == 0 && Double.compare(accumulation, that.accumulation) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), percentAccumulation, accumulation);
    }
}

