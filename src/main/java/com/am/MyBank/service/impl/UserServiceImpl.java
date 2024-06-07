package com.am.MyBank.service.impl;

import com.am.MyBank.model.User;
import com.am.MyBank.repository.UserRepository;


import com.am.MyBank.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.mindrot.jbcrypt.BCrypt;
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

    String encode = BCrypt.gensalt();

    @Override
    public User createUser(User user) {
        User u = new User();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setMiddleName(user.getMiddleName());
        u.setPhoneNumber(user.getPhoneNumber());
        u.setDateOfBirth(user.getDateOfBirth());
        u.setEmail(user.getEmail());

        u.setPassword(user.getPassword());
        u.setPassword(BCrypt.hashpw(user.getPassword(), encode));

        if (userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            return null;
        } else {
            return userRepository.save(u);
        }
    }

    @Override
    public Model getUser(String email, String password, Model model) {

        if (userRepository.findByEmail(email).isEmpty() || !(BCrypt.checkpw(password, userRepository.findByEmail(email).get().getPassword()))) {

            return null;
        } else {

            Optional<User> optional = userRepository.findUserByEmailAndAndPassword(email, BCrypt.hashpw(password, userRepository.findByEmail(email).orElseThrow().getPassword()));

            ArrayList<User> res = new ArrayList<>();
            optional.ifPresent(res::add);

            return model.addAttribute("get", res);
        }
    }
}