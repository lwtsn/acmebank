package com.acmebank.accountmanager.integration.steps;

import com.acmebank.accountmanager.model.BankAccount;
import com.acmebank.accountmanager.model.TransferFundsDto;
import com.acmebank.accountmanager.respository.BankAccountRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountSteps {

    public static final int PORT = 8080;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    BankAccountRepository bankAccountRepository;

    ResponseEntity<BankAccount> queryBankAccountResponse;

    ResponseEntity<Object> transferFundsResponse;

    @When("I check the balance for account {int}")
    public void iCheckTheBalanceForAccount(int accountNumber) {
        String url = format(
                "http://localhost:%d/account/%s",
                PORT,
                accountNumber
        );

        queryBankAccountResponse = restTemplate.getForEntity(url, BankAccount.class);
    }

    @Then("I should see the balance is {int} {string}")
    public void iShouldSeeTheBalanceIs(int balance, String currency) {
        assertEquals(balance, queryBankAccountResponse.getBody().getBalance());
        assertEquals(currency, queryBankAccountResponse.getBody().getCurrency());
    }

    @Given("I have a bank account with id {int} and balance of {int}")
    public void iHaveABankAccountWithIdAndBalanceOf(int accountNumber, int balance) {
        BankAccount bankAccount = new BankAccount((long) accountNumber, (float) balance, "HKD");
        bankAccountRepository.save(bankAccount);
    }

    @When("I transfer {int} from {int} to {int}")
    public void iTransferFromTo(int amountToTransfer, int initiatorAccountNumber, int benefactorAccountNumber) {
        TransferFundsDto transferFundsDto = new TransferFundsDto(
                (long) initiatorAccountNumber,
                (long) benefactorAccountNumber,
                (float) amountToTransfer
        );

        String url = format("http://localhost:%d/transfer", PORT);
        transferFundsResponse = restTemplate.postForEntity(url, transferFundsDto, Object.class);

        System.out.println(transferFundsResponse);
    }
}
