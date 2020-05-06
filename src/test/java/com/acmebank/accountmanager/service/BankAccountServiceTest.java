package com.acmebank.accountmanager.service;

import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.respository.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class BankAccountServiceTest {

    @Mock
    BankAccountRepository bankAccountRepository;

    @InjectMocks
    BankAccountService bankAccountService;

    Long bankAccountId = 123456L;
    private BankAccount bankAccount = new BankAccount();

    @Test
    public void testGetBankAccountByIdReturnsABankAccountWhenFound() {
        given(bankAccountRepository.findById(bankAccountId)).willReturn(Optional.of(bankAccount));
        assertEquals(bankAccountService.getBankAccountById(bankAccountId), bankAccount);
    }
}