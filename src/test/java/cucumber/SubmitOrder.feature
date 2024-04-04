
@tag
Feature: Purchase the order from eCommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on ECommerce page

  @Regression
  Scenario Outline: Positive test for submitting the order
    Given Logged In with username <name> and password <password>
    When I add the product <productName> from Menu
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page

    Examples: 
      | name  											| 	password		 |  productName		 | 
      | alt.gq-eon86v6j@yopmail.com |  Qwerty123@		 | ZARA COAT 3		 |
      #| shetty@gmail.com 						|  Iamking@000 	 | ADIDAS ORIGINAL |
      

      