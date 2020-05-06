package com.acmebank.accountmanager.integration;

import com.acmebank.accountmanager.AccountManagerApplication;
import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = AccountManagerApplication.class, loader = SpringBootContextLoader.class)
public class CucumberConfiguration {
    @Before
    public void setUp() {
        System.out.println("When life gives you cucumbers, make cuceaide");
    }
}