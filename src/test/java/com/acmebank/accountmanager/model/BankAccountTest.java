package com.acmebank.accountmanager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount bankAccount = new BankAccount(123L, 200f, "HKD");

    @Test
    public void testHasSufficientFunds() {
        Float amountToTransfer = 100.20f;
        assertTrue(bankAccount.hasSufficientFundsToTransfer(amountToTransfer));
    }

    @Test
    public void testHasInsufficientFunds() {
        Float amountToTransfer = 9999.0f;
        assertFalse(bankAccount.hasSufficientFundsToTransfer(amountToTransfer));
    }

    @Test
    public void subtractFunds() {
        Float amountToTransfer = 100f;
        BankAccount updatedBankAccount = bankAccount.subtractFunds(amountToTransfer);
        assertEquals(100f, updatedBankAccount.getBalance());
    }

    @Test
    public void addFunds() {
        Float amountToAdd = 100f;
        BankAccount updatedBankAccount = bankAccount.addFunds(amountToAdd);
        assertEquals(300f, updatedBankAccount.getBalance());
    }
}