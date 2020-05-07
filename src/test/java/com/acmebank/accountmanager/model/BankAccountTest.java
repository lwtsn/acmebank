package com.acmebank.accountmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountTest {

    BankAccount bankAccount;

    @Test
    public void testHasSufficientFunds() {
        Float amountToTransfer = 100.20f;
        bankAccount = new BankAccount(123L, 200f, "HKD");
        assertTrue(bankAccount.hasSufficientFundsToTransfer(amountToTransfer));
    }
}