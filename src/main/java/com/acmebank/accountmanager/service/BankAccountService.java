package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.respository.BankAccountRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount getBankAccountById(Long bankAccountId) throws EntityNotFoundException {
        return bankAccountRepository.findById(bankAccountId).orElseThrow(EntityNotFoundException::new);
    }
}