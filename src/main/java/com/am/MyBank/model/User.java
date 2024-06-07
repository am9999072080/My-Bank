package com.am.MyBank.model;

import jakarta.persistence.*;
import lombok.Data;

import org.mindrot.jbcrypt.BCrypt;


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
    private String firstName;
    private String lastName;
    private String middleName;

    @Column(unique = true)
    private String phoneNumber;
    private LocalDate dateOfBirth;
    @Transient
    private int age;

    @Column(unique = true)
    private String email;
    private String password;

    @Transient
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;
    @Transient
    String salt = BCrypt.gensalt();

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
