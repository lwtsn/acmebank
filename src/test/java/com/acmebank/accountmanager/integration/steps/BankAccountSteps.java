package com.acmebank.accountmanager.integration.steps;

import com.acmebank.accountmanager.model.BankAccount;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountSteps {

    @Autowired
    TestRestTemplate restTemplate;

    ResponseEntity<BankAccount> response;

    @When("I check the balance for account {int}")
    public void iCheckTheBalanceForAccount(int accountNumber) {
        String url = format(
                "http://localhost:%d/account/%s",
                8080,
                accountNumber
        );

        response = restTemplate.getForEntity(url, BankAccount.class);
    }

    @Then("I should see the balance is {int} {string}")
    public void iShouldSeeTheBalanceIs(int balance, String currency) {
        assertEquals(balance, response.getBody().getBalance());
        assertEquals(currency, response.getBody().getCurrency());
    }
}
