package com.am.MyBank.model;

import jakarta.persistence.*;
import lombok.Data;

import org.antlr.v4.runtime.misc.NotNull;



import static jakarta.persistence.GenerationType.IDENTITY;
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
//    @NotNull
//    private String firstName;
//    @NotNull
//    private String lastName;
//    private String middleName;
//    @NotNull
//    @Column(unique = true)
//    private String phoneNumber;
//    private LocalDate dateOfBirth;
//    @Transient
//    private int age;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private  String password;




//    @Transient
//    @OneToOne(cascade = CascadeType.ALL)
//    private Card card;
//    public int getAge() {
//        return Period.between(dateOfBirth, LocalDate.now()).getYears();
//    }
//    public void setPhoneNumber(String number) {
//        checkInputNumber(number);
//        this.phoneNumber=number;
//    }
//
//
//    public static String checkInputNumber(String num) throws IllegalArgumentException{
//        String pattern = "^(\\+7|8)(\\d{10})$";
//        if (num.matches(pattern)) {
//            return num + " is valid";
//        } else {
//            throw new IllegalArgumentException("Number is invalid");
//        }
//    }
}
