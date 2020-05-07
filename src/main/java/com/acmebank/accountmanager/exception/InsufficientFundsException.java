package com.acmebank.accountmanager.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(Float fundsToTransfer, Float fundsAvailable) {
        super(String.format(
                "Attempted to transfer %f, only %f available",
                fundsToTransfer,
                fundsAvailable
        ));
    }
}
