package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.respository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class BankAccountServiceTest {

    @Mock
    BankAccountRepository bankAccountRepository;

    @InjectMocks
    BankAccountService bankAccountService;

    private BankAccount bankAccount = new BankAccount();

    private BankAccount recipientBankAccount = new BankAccount();

    private TransferFundsDto transferFundsDto;

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
}