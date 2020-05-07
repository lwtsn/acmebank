package com.acmebank.accountmanager.usecase;

import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.service.BankAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransferFundsUsecaseTest {

    @Mock
    BankAccountService bankAccountService;

    @InjectMocks
    TransferFundsUsecase transferFundsUsecase;

    private final Long initiatorBankAccountId = 123456L;
    private final Long benefactorBankAccountId = 987654L;

    private final Float initiatorBankBalance = 1000F;
    private final Float benefactorBankBalance = 1000F;

    private final Float amountToTransfer = 100F;

    private final String currency = "HKD";

    private final BankAccount initiatorBankAccount = new BankAccount(
            initiatorBankAccountId,
            initiatorBankBalance,
            currency
    );

    private final BankAccount benefactorBankAccount = new BankAccount(
            benefactorBankAccountId,
            benefactorBankBalance,
            currency
    );

    private final TransferFundsDto transferFundsDto = new TransferFundsDto(
            initiatorBankAccountId,
            benefactorBankAccountId,
            amountToTransfer
    );

    @Test
    public void testEntityNotFoundExceptionThrownWhenInitiatorNotFound() {
        given(bankAccountService.getBankAccountById(initiatorBankAccountId)).willThrow(new EntityNotFoundException());

        assertThrows(
                EntityNotFoundException.class, () -> transferFundsUsecase.transferFunds(transferFundsDto)
        );
    }

    @Test
    public void testInsufficientFundsExceptionIsThrownWhenAccountHasLessBalanceThanAttemptedTransfer() {
        BankAccount insufficientFundsBankAccount = initiatorBankAccount.toBuilder().balance(10F).build();
        given(bankAccountService.getBankAccountById(
                initiatorBankAccountId
        )).willReturn(insufficientFundsBankAccount);

        assertThrows(
                InsufficientFundsException.class, () -> transferFundsUsecase.transferFunds(transferFundsDto)
        );
    }

    @Test
    public void testEntityNotFoundExceptionThrownWhenBenefactorNotFound() {
        given(bankAccountService.getBankAccountById(anyLong())).willAnswer(invocationOnMock -> {
            if (invocationOnMock.getArguments()[0] == initiatorBankAccountId) {
                return initiatorBankAccount;
            }

            throw new EntityNotFoundException();
        });

        assertThrows(
                EntityNotFoundException.class, () -> transferFundsUsecase.transferFunds(transferFundsDto)
        );
    }

    @Test
    public void testBalancesAreUpdated() {
        given(bankAccountService.getBankAccountById(anyLong())).willAnswer(invocationOnMock -> {
            if (invocationOnMock.getArguments()[0] == initiatorBankAccountId) {
                return initiatorBankAccount;
            }

            if (invocationOnMock.getArguments()[0] == benefactorBankAccountId) {
                return benefactorBankAccount;
            }
            throw new EntityNotFoundException();

        });

        transferFundsUsecase.transferFunds(transferFundsDto);

        ArgumentCaptor<BankAccount> argumentCaptor = ArgumentCaptor.forClass(BankAccount.class);
        verify(bankAccountService, times(2)).update(argumentCaptor.capture());
        List<BankAccount> bankAccounts = argumentCaptor.getAllValues();

        //Due to the order in which we update, the first bank account is the initiator
        assertEquals(900F, bankAccounts.get(0).getBalance());
        assertEquals(1100F, bankAccounts.get(1).getBalance());
    }


}