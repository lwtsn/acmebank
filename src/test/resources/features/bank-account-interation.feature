Feature: As a client I wish to interact with my bank account

  Scenario: The client checks their bank balance
    Given I am a client
    When I check the balance for account 12345678
    Then I should see the balance is 1000000 "HKD"