package com.am.MyBank.service;

import com.am.MyBank.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.List;


public interface UserService {
    User saveUser(User user);

    User getUserAut(Authentication authentication);

    User getByPhoneNumber(String phone);

    void getUserById(Long id, Model model);

    void userDelete(long id, Model model);
    User updatePassword(Authentication authentication, String newPassword, String repeatPassword);
    List<User> getAll();
}