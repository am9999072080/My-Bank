package com.am.MyBank.model;

import jakarta.persistence.*;
import lombok.Data;

import org.antlr.v4.runtime.misc.NotNull;


import java.time.LocalDate;
import java.time.Period;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String middleName;
    @NotNull
    @Column(unique = true)
    private String phoneNumber;
    private LocalDate dateOfBirth;
    @Transient
    private int age;

    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;


    @Transient
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
