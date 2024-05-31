package com.am.MyBank.controller;

import com.am.MyBank.model.User;
import com.am.MyBank.repository.UserRepository;
import com.am.MyBank.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    private UserRepository repository;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    public String login(Model model) {
        Iterable<User> userAdd = repository.findAll();
        model.addAttribute("userAdd", userAdd);
//        return "reg/user";
        return "reg/user-add-main";
    }

    @GetMapping("/user/add")
    public String userAdd(Model model) {
        return "reg/user-add";
    }

    @PostMapping("/user/add")
    public String userPostAdd(@Validated User user, Model model) {
        userService.addUser(user);
        return "redirect:/registration";
    }


    @GetMapping("/get/user{email}/user{password}")
    public String getUser(@PathVariable("email") String email,
                          @PathVariable("password") String password, Model model) {
        Iterable<User> userGet = repository.findAll();
        model.addAttribute("userGet", userGet);
        userService.getUser(email, password, model);
        return "reg/user-details";
    }
}