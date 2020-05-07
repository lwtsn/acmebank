package com.acmebank.accountmanager.usecase;

import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.service.BankAccountService;
import org.springframework.stereotype.Service;

@Service
public class TransferFundsUsecase {

    BankAccountService bankAccountService;

    public TransferFundsUsecase(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    public void transferFunds(TransferFundsDto transferFundsDto) {
        throw new InsufficientFundsException();
    }
}
