Feature: Step contract definition

  Scenario: Edit contract
    Given a Process with a human task
    When I edit the human task
    Then I can edit its contract

  Scenario: Reorder input of a contract
    Given a Process with a human task
    When I edit the contract of the task
    Then I can re-order Inputs

  Scenario: Add a input to a contract
    Given a Process with a human task
    When I edit the contract of the task
    Then I add new Inputs by specifying its "Name","Type","Description" and "Validators"

  Scenario: Edit an existing input
    Given a Process with a human task
    When I edit the contract of the task
    Then I update existing Inputs by modifying its
      | Name | Type | Description | Validators |

  Scenario: Remove an existing input
    Given a Process with a human task
    When I edit the contract of the task
    Then I can remove Inputs

  Scenario: Remove an existing Validator
    Given a Process with a human task
    When I edit the contract of the task
    Then I can remove Validators
