package com.am.MyBank.controller;

import com.am.MyBank.model.Card;
import com.am.MyBank.repository.DebitRepository;
import com.am.MyBank.service.CreditService;
import com.am.MyBank.service.impl.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CreditController {
    private final CreditService service;
    public Card card;


    @Autowired
    private DebitRepository repository;

    public CreditController(CreditServiceImpl service) {
        this.service = service;
    }


    @GetMapping("/credit")
    public String debitAddMain(Model model) {
        Iterable<Card> cardAdd = repository.findAll();
        model.addAttribute("cardAdd", cardAdd);
        return "credit-add-main";
    }

    @GetMapping("/credit/add")
    public String debitAdd(Model model) {
        return "credit-add";
    }

    @PostMapping("/credit/add")
    public String debitPostAdd(@RequestParam double balance, Model model) {
        service.addBalance(balance);
        return "redirect:/credit";
    }

    @GetMapping("/credit/pay")
    public String debitPay(Model model) {
        return "credit-add";
    }

    @PostMapping("/credit/pay")
    public String debitPostPay(@RequestParam double balance, Model model) {

        service.pay(balance);

        return "redirect:/credit";
    }

    @GetMapping("/get/credit")
    public String get(Model model) {
        Iterable<Card> cardGet = repository.findAll();
        model.addAttribute("cardGet", cardGet);
        service.getAllBalance();
        return "credit-get-main";
    }

    @GetMapping("/get/all/credit")
    public String getAll(Model model) {
        return "redirect:/get/credit";
    }
}

