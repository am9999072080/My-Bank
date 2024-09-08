package com.am.MyBank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    double balance;
    @Transient
    private final double creditLimit = 10_000;

    private double creditBalance = creditLimit;
    private double cashBack;
    private double bonus;

    String allBalance;

    public LocalDateTime dateTime;


    public String getDate() {
        setDateTime(LocalDateTime.now());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss  dd-MMMM-yyyy год.");

        return getDateTime().format(format);
    }

}
