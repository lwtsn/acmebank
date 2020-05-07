package com.acmebank.accountmanager.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Float balance;

    String currency;

    public boolean hasSufficientFundsToTransfer(Float amountToTransfer) {
        return balance >= amountToTransfer;
    }

    public BankAccount subtractFunds(Float amountToTransfer) {
        Float updatedBalance = balance - amountToTransfer;

        return this.toBuilder().balance(updatedBalance).build();
    }

    public BankAccount addFunds(Float amountToTransfer) {
        Float updatedBalance = balance + amountToTransfer;

        return this.toBuilder().balance(updatedBalance).build();
    }
}
