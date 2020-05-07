package com.acmebank.accountmanager.usecase;

import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TransferFundsUsecaseTest {

    @Mock
    BankAccountService bankAccountService;

    @InjectMocks
    TransferFundsUsecase transferFundsUsecase;

    TransferFundsDto transferFundsDto;

    @Test
    public void testInsufficientFundsExceptionIsThrownWhenAccountHasLessBalanceThanAttemptedTransfer() {
        assertThrows(
                InsufficientFundsException.class, () -> transferFundsUsecase.transferFunds(transferFundsDto)
        );
    }

//    @Test
//    public void testBalancesAreUpdated() {
//        assertThrows(
//                InsufficientFundsException.class, () -> bankAccountService.transferFunds(transferFundsDto)
//        );
//    }




}