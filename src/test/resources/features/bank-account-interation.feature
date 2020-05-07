Feature: As a client I wish to interact with my bank account

  Scenario: The client checks their bank balance
    When I check the balance for account 12345678
    Then I should see the balance is 1000000 "HKD"

  Scenario: I instruct a transfer from one account to another
    Given I have a bank account with id 12345678 and balance of 1000000
    And I have a bank account with id 88888888 and balance of 1000000
    When I transfer 10000 from 12345678 to 88888888
    Then I check the balance for account 12345678
    And I should see the balance is 990000 "HKD"
    Then I check the balance for account 88888888
    And I should see the balance is 1010000 "HKD"
