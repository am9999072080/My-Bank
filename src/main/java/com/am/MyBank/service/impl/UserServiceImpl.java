package com.am.MyBank.service.impl;

import com.am.MyBank.debit.DebitGold;
import com.am.MyBank.model.Card;
import com.am.MyBank.model.User;

import com.am.MyBank.repository.DebitRepository;
import com.am.MyBank.repository.UserRepository;

import com.am.MyBank.service.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DebitRepository debitRepository;

    private BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public User saveUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            return null;
        } else {
            Card card = new Card();
            user.setCard(new DebitGold(card).getCard());
            debitRepository.save(card);
            user.setPassword(encoder().encode(user.getPassword()));
            return userRepository.save(user);
        }
    }


    @Override
    public User getUserAut(Authentication authentication) {
        Optional<User> optional = userRepository.findByEmail(authentication.getName());
        ArrayList<User> res = new ArrayList<>();
        optional.ifPresent(res::add);
        return res.get(authentication.getName().indexOf(authentication.getName()));
    }


    @Override
    public void getUserById(Long id, Model model) {
        Optional<User> u = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        u.ifPresent(res::add);
        model.addAttribute("user", res);
    }

    @Override
    public User getByPhoneNumber(String phone) {
        Optional<User> optional = userRepository.findByPhoneNumber(phone);
        ArrayList<User> res = new ArrayList<>();
        optional.ifPresent(res::add);
        if (res.stream().filter(p -> p.getPhoneNumber().equals(phone)).findFirst().isEmpty()) {
            return null;
        } else {
            return res.stream().filter(p -> p.getPhoneNumber().equals(phone)).findFirst().orElseThrow();
        }
    }

    @Override
    public void userDelete(long id, Model model) {
        User user = userRepository.findById(id).orElseThrow();
        if (userRepository.findAll().size() > 1) {
            userRepository.delete(user);
        } else {
            userRepository.findById(id);
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User updatePassword(Authentication authentication, String newPassword, String repeatPassword) {
        User user = getUserAut(authentication);
        if (!newPassword.equals(repeatPassword)) {
            return null;
        } else {
            user.setPassword(encoder().encode(newPassword));
            return userRepository.save(user);
        }
    }
}

