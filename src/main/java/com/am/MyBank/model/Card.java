package com.am.MyBank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    double balance;
    private final double creditLimit = 10_000;
    private double creditBalance = creditLimit;
    double bonus;
    double accumulation;
    String date;
    String allBalance;
}
