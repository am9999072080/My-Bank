package com.am.MyBank.controller;

import com.am.MyBank.model.User;

import com.am.MyBank.repository.UserRepository;
import com.am.MyBank.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/registration")
    public String login(Model model) {
        Iterable<User> userAdd = userRepository.findAll();
        model.addAttribute("userAdd", userAdd);
        return "reg/user-add-main";
    }

    @GetMapping("/user/add")
    public String userAdd(Model model) {
        return "reg/user-add";
    }

    @PostMapping("/user/add")
    public String userPostAdd(@Validated User user, Model model) {
        if (userService.addUser(user) == null) {
            return "reg/errors-user-add";
        } else if (user.getAge() < 18) {
            userService.addUser(user);
            return "reg/errors-small-user";
        } else {
            userService.addUser(user);
            return "redirect:/registration";
        }
    }

    @GetMapping("/get/user")
    public String getUser(@RequestParam String email, @Validated String password, Model model) {
        if (userService.getUser(email, password, model) == null) {
            return "reg/errors-user-get";
        } else if (userRepository.findUserByEmailAndAndPassword(email, password).stream()
                .filter(e -> e.getAge() > 18)
                .findFirst().isEmpty()) {
            return "reg/errors-small-user";
        } else {
            userService.getUser(email, password, model);
            System.out.println(email + "   " + password);
            return "reg/user-details";
        }
    }
}