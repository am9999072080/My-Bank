package com.am.MyBank.controller;
import com.am.MyBank.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/home")
    public String iCan(Model model) {
        model.addAttribute("title", "_BEST MyBank ACCOUNT_");
        return "home";
    }

    @GetMapping("/exchange")
    public String Exchange(Model model) {
        model.addAttribute("title", "_Exchange_");
        return "exchange";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Преимущества MyBank карт и недостатки НЕ MyBank карт");
        return "about";
    }
}