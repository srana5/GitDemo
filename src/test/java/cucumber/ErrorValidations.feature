
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidations
  Scenario Outline: When I enter Incorrect password
    Given I landed on ECommerce page
    When Logged In with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  											| 	password		 |
      | alt.gq-eon86v6j@yopmail.com |  Qwerty23@		 |
