package com.acmebank.accountmanager.usecase;

import com.acmebank.accountmanager.exception.InsufficientFundsException;
import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class TransferFundsUsecase {

    private final BankAccountService bankAccountService;

    public TransferFundsUsecase(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    public void transferFunds(TransferFundsDto transferFundsDto)
            throws EntityNotFoundException, InsufficientFundsException {
        BankAccount initiatorBankAccount = fetchBankAccountById(transferFundsDto.getInitiatorId());

        validateSufficientFunds(transferFundsDto, initiatorBankAccount);

        BankAccount benefactorBankAccount = fetchBankAccountById(transferFundsDto.getBenefactorId());

        updateBalances(transferFundsDto, initiatorBankAccount, benefactorBankAccount);
    }

    private void updateBalances(TransferFundsDto transferFundsDto, BankAccount initiatorBankAccount, BankAccount benefactorBankAccount) {
        BankAccount updatedInitiatorBankAccount = initiatorBankAccount.subtractFunds(
                transferFundsDto.getAmountToTransfer()
        );

        BankAccount updatedBenefactorBankAccount = benefactorBankAccount.addFunds(
                transferFundsDto.getAmountToTransfer()
        );

        bankAccountService.update(updatedInitiatorBankAccount);
        bankAccountService.update(updatedBenefactorBankAccount);
    }

    private void validateSufficientFunds(TransferFundsDto transferFundsDto, BankAccount initiatorBankAccount) {
        if (!initiatorBankAccount.hasSufficientFundsToTransfer(transferFundsDto.getAmountToTransfer())) {
            throw new InsufficientFundsException(
                    transferFundsDto.getAmountToTransfer(),
                    transferFundsDto.getAmountToTransfer()
            );
        }
    }

    private BankAccount fetchBankAccountById(Long initiatorId) {
        try {
            return bankAccountService.getBankAccountById(initiatorId);
        } catch (EntityNotFoundException exception) {
            log.warn(String.format("Bank account %d not found", initiatorId));
            throw exception;
        }
    }
}
