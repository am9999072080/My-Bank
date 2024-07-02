package com.am.MyBank.controller;

import com.am.MyBank.service.DebitService;
import com.am.MyBank.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class DebitController {
    @Autowired
    DebitService service;
    @Autowired
    UserService userService;

    @GetMapping("/debit")
    public String debitAddMain(Model model) {
        model.addAttribute("card", service.getAll());
        return "redirect:/";

    }

    @GetMapping("/debit/add")
    public String debitAdd(Model model) {
        return "/aut";
    }

    @PostMapping("/debit/add")
    public String debitPostAdd(@RequestParam double balance, Authentication authentication, Model model) {
        service.addBalance(balance, authentication);
        return "redirect:/aut";
    }


    @GetMapping("/debit/pay")
    public String debitPay(Model model) {
        return "/aut";
    }

    @PostMapping("/debit/pay")
    public String debitPostPay(@RequestParam double balance, Authentication authentication, Model model) {
        if (userService.getUserAut(authentication).getCard().getBalance() < balance) {
            return "card/error-transaction";
        } else {
            service.pay(balance, authentication);
            return "redirect:/aut";
        }
    }

    @GetMapping("/debit/send")
    public String transferByPhone(Model model) {
        return "/aut";
    }

    @PostMapping("/debit/send")
    public String transferByPhone(@RequestParam double balance, @RequestParam String phoneNumber, Authentication authentication, Model model) {
        if (userService.getByPhoneNumber(phoneNumber) == null) {
            return "reg/error-phone-number";
        } else if (userService.getUserAut(authentication).getCard().getBalance() < balance) {
            return "card/error-transaction";
        } else {
            service.sendByPhone(balance, phoneNumber, authentication);
            return "redirect:/aut";
        }
    }
}

