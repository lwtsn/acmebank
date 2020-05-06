package com.acmebank.accountmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {

    @GetMapping("/account/{accountNumber}")
    public String getVersion(@PathVariable String accountNumber) {
        return "";
    }
}
