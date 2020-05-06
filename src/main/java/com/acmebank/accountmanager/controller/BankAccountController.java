package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.service.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankAccountController {

    private BankAccountService bankAccountService;

    BankAccountController(BankAccountService bankAccountService) {

        this.bankAccountService = bankAccountService;
    }


    @GetMapping("/account/{bankAccountNumber}")
    public BankAccount getVersion(@PathVariable Long bankAccountNumber) {
        return bankAccountService.getBankAccountById(bankAccountNumber);
    }
}
