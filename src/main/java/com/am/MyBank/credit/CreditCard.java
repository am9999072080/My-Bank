package com.am.MyBank.credit;

import com.am.MyBank.BankCard;
import com.am.MyBank.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CreditCard extends BankCard {

    private final double creditLimit = 10_000;
    private double creditBalance = creditLimit;

    public CreditCard(Card card) throws RuntimeException {
        super(card);
    }


    @Override
    public void addBalance(double amount) throws RuntimeException {
        if (amount <= 0) {
            throw new RuntimeException("Введенное число должен быть > 0");
        }
        double d;
        if (creditBalance + amount <= creditLimit) {
            creditBalance = creditBalance + amount;
            System.out.print("ПОПОЛНЕНИЕ: " + amount + ", DEB: 0.0, CRED: " + amount);
        } else {
            d = creditBalance + amount - creditLimit;
            creditBalance = creditLimit;

            getCard().setBalance(getCard().getBalance() + d);
            System.out.print("ПОПОЛНЕНИЕ: " + amount + ", DEB: " + d + ", CRED: " + (amount - d));
            System.out.print(", Остаток кредитных средств " + creditLimit);
        }
    }

    @Override
    public double checkBalance() {
        System.out.print(" ");
        return getCard().getBalance() + creditBalance;
    }

    @Override
    public boolean pay(double amount) {
        double d;
        if (!(getCard().getBalance() + creditBalance < amount)) {
            if (getCard().getBalance() >= amount) {
                getCard().setBalance(getCard().getBalance() - amount);
                System.out.print("ПОКУПКА: " + amount + ", DEB: " + -amount + ", CRED: -0.0");
            } else if (amount <= getCard().getBalance() + creditBalance) {
                d = amount - getCard().getBalance();
                System.out.print("ПОКУПКА: " + amount);
                System.out.print(", DEB: " + -getCard().getBalance());
                getCard().setBalance(0);
                creditBalance = creditBalance - d;
                System.out.print(", CRED: " + -d);
            }
        } else {
            throw new RuntimeException("Недостаточно средств!");
        }
        return super.pay(amount);
    }

    @Override
    public String checkAllBalance() {
        return "\nDEBIT: " + String.format("%.2f", getCard().getBalance()) + " CREDIT: " + String.format("%.2f", creditBalance);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CreditCard that = (CreditCard) o;
        return Double.compare(creditLimit, that.creditLimit) == 0 && Double.compare(creditBalance, that.creditBalance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), creditLimit, creditBalance);
    }
}

