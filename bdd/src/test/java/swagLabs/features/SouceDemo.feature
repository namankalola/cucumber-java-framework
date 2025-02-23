@Login
Feature: Login and Logout
  @Login
  Scenario: Login with valid username and password
    Given User is on Swag Labs login page
    And I want to read test data from "TEST01" sheet
    When User enters username "app.username" and password "app.password"
    And User clicks on Login button
    And Products page displayed
    When User clicks on humbergur menu and Logout link
    And Swag Labs Login page displayed

  @Login
  Scenario: Login with valid locked_out_user
    Given User is on Swag Labs login page
    And I want to read test data from "TEST02" sheet
    When User enters username "locked_out_user" and password "app.password"
    And User clicks on Login button
    And This user has been locked out - message displayed

  @Login
  Scenario: Login with valid locked_out_user - 1
    Given User is on Swag Labs login page
    And I want to read test data from "TEST02" sheet
    When User enters username "locked_out_user" and password "app.password"
    And User clicks on Login button
    And This user has been locked out - message displayed