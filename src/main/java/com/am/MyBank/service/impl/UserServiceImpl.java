package com.am.MyBank.service.impl;

import com.am.MyBank.model.User;
import com.am.MyBank.repository.UserRepository;

import com.am.MyBank.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) {
        User u = new User();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setMiddleName(user.getMiddleName());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setDateOfBirth(user.getDateOfBirth());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        if (userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            return null;
        } else {
            return userRepository.save(u);
        }
    }

    @Override
    public Model getUser(String email, String password, Model model) {

        Optional<User> optional = userRepository.findUserByEmailAndAndPassword(email, password);
        ArrayList<User> res = new ArrayList<>();
        optional.ifPresent(res::add);
        System.out.println("res" + res);
        System.out.println("email" + email + "String password" + password);
        if (res.stream().filter(e -> e.getEmail().equals(email)).filter(p -> p.getPassword().equals(password)).findFirst().orElse(null) == null) {
            return null;
        } else {
            return model.addAttribute("get", res);
        }
    }
}
