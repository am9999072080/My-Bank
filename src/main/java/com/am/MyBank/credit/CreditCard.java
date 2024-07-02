//package com.am.MyBank.credit;
//
//
//import com.am.MyBank.model.BankCard;
//import com.am.MyBank.model.Card;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//public class CreditCard extends BankCard {
//
//    public CreditCard(Card card) throws RuntimeException {
//        super(card);
//    }
//
//    @Override
//    public void addBalance(double amount) throws RuntimeException {
////        addCreditBalance();
//        if (amount <= 0) {
//            throw new RuntimeException("Введенное число должен быть > 0");
//        }
//        double d;
//        if (getCard().getCreditBalance() + amount <= getCard().getCreditLimit()) {
//            getCard().setCreditBalance(getCard().getCreditBalance() + amount);
//            System.out.print("ПОПОЛНЕНИЕ: " + amount + ", DEB: 0.0, CRED: " + amount);
//        } else {
//            d = getCard().getCreditBalance() + amount - getCard().getCreditLimit();
//            getCard().setCreditBalance(getCard().getCreditLimit());
//            getCard().setBalance(getCard().getBalance() + d);
//            System.out.print("ПОПОЛНЕНИЕ: " + amount + ", DEB: " + d + ", CRED: " + (amount - d));
//            System.out.print(", Остаток кредитных средств " + getCard().getCreditLimit());
//        }
//    }
//
//    @Override
//    public double checkBalance() {
//        System.out.print(" ");
//        return getCard().getBalance() + getCard().getCreditBalance();
//    }
//
//    @Override
//    public boolean pay(double amount) {
//        double d;
//        if (!(getCard().getBalance() + getCard().getCreditBalance() < amount)) {
//            if (getCard().getBalance() >= amount) {
//                getCard().setBalance(getCard().getBalance() - amount);
//                System.out.print("ПОКУПКА: " + amount + ", DEB: " + -amount + ", CRED: -0.0");
//            } else if (amount <= getCard().getBalance() + getCard().getCreditBalance()) {
//                d = amount - getCard().getBalance();
//                System.out.print("ПОКУПКА: " + amount);
//                System.out.print(", DEB: " + -getCard().getBalance());
//                getCard().setBalance(0);
//                getCard().setCreditBalance(getCard().getCreditBalance() - d);
//                System.out.print(", CRED: " + -d);
//            }
//        } else {
//            throw new RuntimeException("Недостаточно средств!");
//        }
//        return super.pay(amount);
//    }
//
//    @Override
//    public String checkAllBalance() {
//        return "\nDEBIT: " + String.format("%.2f", getCard().getBalance()) + " CREDIT: " + String.format("%.2f", getCard().getCreditBalance());
//    }
//}
//
