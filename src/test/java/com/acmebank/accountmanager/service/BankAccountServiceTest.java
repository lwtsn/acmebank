package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.respository.BankAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class BankAccountServiceTest {

    @Mock
    BankAccountRepository bankAccountRepository;

    @InjectMocks
    BankAccountService bankAccountService;

    private final BankAccount bankAccount = new BankAccount(12345678L, 400F, "HKD");

    Long bankAccountId = 123456L;

    @Test
    public void testGetBankAccountByIdReturnsABankAccountWhenFound() {
        given(bankAccountRepository.findById(bankAccountId)).willReturn(Optional.of(bankAccount));
        assertEquals(bankAccountService.getBankAccountById(bankAccountId), bankAccount);
    }

    @Test
    public void testGetBankAccountByIdReturnsThrowsExceptionWhenBankAccountDoesNotExist() {
        given(bankAccountRepository.findById(bankAccountId)).willReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class, () -> bankAccountService.getBankAccountById(bankAccountId)
        );
    }

    @Test
    public void testUpdate() {
        when(bankAccountRepository.save(
                Mockito.any(BankAccount.class)
        )).thenAnswer(invocation -> invocation.getArguments()[0]);
        BankAccount updatedBankAccount = bankAccountService.update(bankAccount);

        Assertions.assertEquals(updatedBankAccount, bankAccount);
    }
}