package com.acmebank.accountmanager.respository;

import com.acmebank.accountmanager.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {

    Optional<BankAccount> findById(Long id);
}
