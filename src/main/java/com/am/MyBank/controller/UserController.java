package com.am.MyBank.controller;

import com.am.MyBank.model.User;
import com.am.MyBank.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/")
    public String getUserAut(Authentication authentication, Model model) {
        if (userService.getUserAut(authentication).getAge() < 18) {
            return "reg/errors-small-user";
        } else {
            model.addAttribute("users", userService.getAll());
            return "reg/main";
        }
    }

    @GetMapping("/aut")
    public String aut(Authentication authentication, Model model) {
        model.addAttribute("me", userService.getUserAut(authentication));
        return "reg/main";
    }

    @GetMapping("/login")
    public String login() {
        return "reg/login";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "reg/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        if (userService.saveUser(user) == null) {
            return "reg/errors-user-add";
        } else if (user.getAge() < 18) {
            return "reg/error-small-reg";
        } else {
            return "reg/user-home";
        }
    }

    @GetMapping("/user/{id}/remove")
    public String getAccount(@PathVariable(value = "id") Long id, Model model) {
        userService.getUserById(id, model);
        return "/";
    }

    @PostMapping("/user/{id}/remove")
    public String deleteAccount(@PathVariable(value = "id") Long id, Model model) {
        userService.userDelete(id, model);
        return "redirect:/";
    }


    @GetMapping("/change-password")
    public String updatePassword() {
        return "reg/change-pass";
    }
        @PostMapping("/change-password")
    public String updatePassword(Authentication authentication,
                                 @RequestParam String newPassword,
                                 @RequestParam String repeatPassword) {
        if((userService.updatePassword(authentication, newPassword, repeatPassword))!=null) {
            return "redirect:/";
        }else {
            return "redirect:/logout";
        }
    }
}