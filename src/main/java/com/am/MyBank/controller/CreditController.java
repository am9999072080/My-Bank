package com.am.MyBank.controller;

import com.am.MyBank.service.CreditService;
import com.am.MyBank.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CreditController {
    @Autowired
    CreditService service;
    @Autowired
    UserService userService;

    @GetMapping("/credit")
    public String creditAddMain(Model model) {
        model.addAttribute("card", service.getAll());
        return "redirect:/";

    }

    @GetMapping("/credit/add")
    public String creditAdd(Model model) {
        return "/aut";
    }

    @PostMapping("/credit/add")
    public String creditPostAdd(@RequestParam double balance, Authentication authentication, Model model) {
        service.addBalance(balance, authentication);
        return "redirect:/aut";
    }


    @GetMapping("/credit/pay")
    public String creditPay(Model model) {
        return "/aut";
    }

    @PostMapping("/credit/pay")
    public String creditPostPay(@RequestParam double balance, Authentication authentication, Model model) {
        if (service.pay(balance, authentication) == null) {

            return "card/error-transaction";

        } else {
            return "redirect:/aut";
        }
    }

    @GetMapping("/credit/send")
    public String transferByPhone(Model model) {
        return "/aut";
    }

    @PostMapping("/credit/send")
    public String transferByPhone(@RequestParam double balance, @RequestParam String phoneNumber, Authentication authentication, Model model) {
        if (userService.getByPhoneNumber(phoneNumber) == null) {
            return "reg/error-phone-number";
        } else {
            service.sendByPhone(balance, phoneNumber, authentication);
            return "redirect:/aut";
        }
    }
}