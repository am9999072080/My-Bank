package com.am.MyBank.controller;

import com.am.MyBank.model.Card;
import com.am.MyBank.repository.DebitRepository;
import com.am.MyBank.service.DebitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DebitController {

    private final DebitService service;
    public Card card;


    @Autowired
    private DebitRepository repository;

    public DebitController(DebitService service) {
        this.service = service;
    }


    @GetMapping("/debit")
    public String debitAddMain(Model model) {
        Iterable<Card> cardAdd = repository.findAll();
        model.addAttribute("cardAdd", cardAdd);
        return "debit-add-main";
    }

    @GetMapping("/debit/add")
    public String debitAdd(Model model) {
        return "debit-add";
    }

    @PostMapping("/debit/add")
    public String debitPostAdd(@RequestParam double balance, Model model) {
        service.addBalance(balance);
        return "redirect:/debit";
    }

    @GetMapping("/debit/pay")
    public String debitPay(Model model) {
        return "debit-add";
    }

    @PostMapping("/debit/pay")
    public String debitPostPay(@RequestParam double balance, Model model) {

        service.pay(balance);

        return "redirect:/debit";
    }

    @GetMapping("/get")
    public String get(Model model) {
        Iterable<Card> cardGet = repository.findAll();
        model.addAttribute("cardGet", cardGet);
        service.getAllBalance();
        return "debit-get-main";
    }

    @GetMapping("/get/all")
    public String getAll(Model model) {
        return "redirect:/get";
    }
}
