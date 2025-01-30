@Login
Feature: Login and Logout
  @Login1
  Scenario: Login with valid username and password
    Given User is on OrangeHRM login page
    When User enters username "Admin" and password "admin123"
    And User clicks on Login button
