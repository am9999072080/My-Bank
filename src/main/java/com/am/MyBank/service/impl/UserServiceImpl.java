package com.am.MyBank.service.impl;

import com.am.MyBank.model.User;
import com.am.MyBank.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    private final UserRepository userRepository;
    private String email;
    private String password;

    public User addUser(User user) {
        User u = new User();
//        u.setFirstName(user.getFirstName());
//    u.setLastName(user.getLastName());
//    u.setMiddleName(user.getMiddleName());
//    u.setPhoneNumber(user.getPhoneNumber());
//    u.setDateOfBirth(user.getDateOfBirth());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
//    u.setCard(user.getCard());
        return userRepository.save(u);
    }
    public User getUser(String email, String password, Model model) {
        Optional<User> optional = userRepository.findUserByEmailAndAndPassword(email, password);
        ArrayList<User> res = new ArrayList<>();
        optional.ifPresent(res::add);
        User us = res.stream().filter(e -> e.getEmail().equals(email)).filter(p -> p.getPassword().equals(password)).findFirst().orElse(null);
        return us;
    }
}
