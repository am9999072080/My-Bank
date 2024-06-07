package com.am.MyBank.service;

import com.am.MyBank.model.User;
import org.springframework.ui.Model;

public interface UserService {
    User createUser(User user);

    Model getUser(String email, String password, Model model);
}