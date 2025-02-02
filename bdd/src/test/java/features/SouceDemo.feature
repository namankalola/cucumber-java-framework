@Login
Feature: Login and Logout
  @Login1
  Scenario: Login with valid username and password
    Given User is on Swag Labs login page
    When User enters username "app.username" and password "app.password"
    And User clicks on Login button
    And Products page displayed
    When User clicks on humbergur menu and Logout link
    And Swag Labs Login page displayed