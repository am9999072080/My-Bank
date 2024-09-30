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
    private final DebitService service;
    @Autowired
    private final UserService userService;

    @GetMapping("/debit")
    public String debitAddMain(Model model) {
        model.addAttribute("card", service.getAll());
        return "redirect:/";

    }

    @PostMapping("/debit/add")
    public String debitPostAdd(@RequestParam double balance, Authentication authentication) {
        service.addBalance(balance, authentication);
        return "redirect:/aut";
    }

    @PostMapping("/debit/pay")
    public String debitPostPay(@RequestParam double balance, Authentication authentication) {
        if (service.pay(balance, authentication) == null) {
            return "card/error-transaction";
        } else {
            return "redirect:/aut";
        }
    }

    @PostMapping("/debit/send")
    public String transferByPhone(@RequestParam double balance, @RequestParam String phoneNumber, Authentication authentication) {
        if ((userService.getByPhoneNumber(phoneNumber) == null) || (userService.getByPhoneNumber(phoneNumber) == userService.getUserAut(authentication))) {
            return "reg/error-phone-number";
        } else if (service.pay(balance, authentication) != null) {
            service.sendByPhone(balance, phoneNumber, authentication);
            return "redirect:/aut";
        } else {
            return "card/error-transaction";
        }
    }
}