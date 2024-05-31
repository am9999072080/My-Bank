package com.am.MyBank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "_BEST BANK ACCOUNT_");
        return "home";
    }

    @GetMapping("/exchange")
    public String Exchange(Model model) {
        model.addAttribute("title", "_Exchange_");
        return "exchange";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Преимущества и недостатки банковских карт");
        return "about";
    }

    @GetMapping("/login")
    public String log(Model model) {
        model.addAttribute("title", "_Login_");
        return "reg/user";
    }

}
