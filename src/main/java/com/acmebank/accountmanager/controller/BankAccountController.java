package com.acmebank.accountmanager.controller;

import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.service.BankAccountService;
import com.acmebank.accountmanager.usecase.TransferFundsUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

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
        try {
            return bankAccountService.getBankAccountById(bankAccountNumber);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transfer")
    public void getVersion(@RequestBody TransferFundsDto transferFundsDto) {
        try {
            transferFundsUsecase.transferFunds(transferFundsDto);
        } catch (InsufficientFundsException exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    exception.getMessage()
            );
        }
    }
}
