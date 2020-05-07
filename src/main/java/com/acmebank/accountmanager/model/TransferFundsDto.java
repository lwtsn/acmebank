package com.acmebank.accountmanager.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransferFundsDto {
    final Long initiatorId;
    final Long benefactorId;
    final Float amountToTransfer;
}
