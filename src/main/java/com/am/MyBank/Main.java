package com.am.MyBank;


import com.am.MyBank.debit.DebitGold;
import com.am.MyBank.model.BankCard;
import com.am.MyBank.model.Card;

public class Main {
    public static void main(String[] args) {
        BankCard debitGold = new DebitGold(new Card());
//        BankCard creditAccumulation = new CreditAccumulation(new Card());

//        System.out.println("---CREDIT-DEBIT---");
//        System.out.println(creditAccumulation.checkAllBalance());
//        creditAccumulation.addBalance(5000);
//        System.out.println(creditAccumulation.checkAllBalance());
//        creditAccumulation.pay(5000);
//        System.out.println(creditAccumulation.checkAllBalance());
//        creditAccumulation.pay(3000);
//        System.out.println(creditAccumulation.checkAllBalance());
//        creditAccumulation.addBalance(2000);
//        System.out.println(creditAccumulation.checkAllBalance());
//        creditAccumulation.addBalance(2000);
//        System.out.println(creditAccumulation.checkAllBalance());


        System.out.println("\n---DEBIT---");

        System.out.println(debitGold.checkAllBalance());
        debitGold.addBalance(15000);
        System.out.println(debitGold.checkAllBalance());
        debitGold.pay(5000);
        System.out.println(debitGold.checkAllBalance());
        debitGold.pay(3000);
        System.out.println(debitGold.checkAllBalance());
        debitGold.addBalance(2000);
        System.out.println(debitGold.checkAllBalance());
        debitGold.addBalance(2000);
        System.out.println(debitGold.checkAllBalance());
        debitGold.pay(10000);
        System.out.println(debitGold.checkAllBalance());
    }
}
