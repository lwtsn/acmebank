package com.acmebank.accountmanager.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Float balance;
}
