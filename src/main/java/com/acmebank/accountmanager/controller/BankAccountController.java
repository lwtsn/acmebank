package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.service.BankAccountService;
import com.acmebank.accountmanager.usecase.TransferFundsUsecase;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankAccountController {

    private final BankAccountService bankAccountService;

    private final TransferFundsUsecase transferFundsUsecase;

    BankAccountController(BankAccountService bankAccountService, TransferFundsUsecase transferFundsUsecase) {
        this.bankAccountService = bankAccountService;
        this.transferFundsUsecase = transferFundsUsecase;
    }

    @GetMapping("/account/{bankAccountNumber}")
    public BankAccount getVersion(@PathVariable Long bankAccountNumber) {
        return bankAccountService.getBankAccountById(bankAccountNumber);
    }

    @PostMapping("/transfer")
    public void getVersion(@RequestBody TransferFundsDto transferFundsDto) {
        transferFundsUsecase.transferFunds(transferFundsDto);
    }
}
