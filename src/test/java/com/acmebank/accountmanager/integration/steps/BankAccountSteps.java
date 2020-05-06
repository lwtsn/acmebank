package com.acmebank.accountmanager.integration.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountSteps {

    @Autowired
    TestRestTemplate restTemplate;

    ResponseEntity<String> response;

    @Given("I am a client")
    public void iAmAClient() {

    }

    @When("I check the balance for account {int}")
    public void iCheckTheBalanceForAccount(int accountNumber) {
        String url = format(
                "http://localhost:%d/account/%s",
                8080,
                accountNumber
        );

        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("I should see the balance is {int} {string}")
    public void iShouldSeeTheBalanceIs(int balance, String currency) {
        assertTrue(response.getStatusCode().equals(HttpStatus.valueOf(200)));
    }
}
