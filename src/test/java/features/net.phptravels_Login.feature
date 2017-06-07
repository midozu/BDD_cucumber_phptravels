Feature: Verify function of Login feature

  Background: Open the Login page
    When I choose the Login option from My Account menu

  Scenario: TC_01_Not_input_email_and_password
    Given I choose the LOGIN button
    Then An error message "Invalid Email or Password" will be displayed

  Scenario: TC_02_LogIn_with_a_valid_email_format_without_password
    Given I only input an valid email as "thanhtruong@@"
    And I choose the LOGIN button
    Then An error message "Invalid Email or Password" will be displayed

  Scenario: TC_03_Login_with_a_password_without_email
    Given I only input a password as "123123"
    And I choose the LOGIN button
    Then An error message "Invalid Email or Password" will be displayed

  Scenario Outline: TC_04_Login_with_an_invalid_account
    Given I input an invalid account with user name <username> and password <password>
    And I choose the LOGIN button
    Then An error message "Invalid Email or Password" will be displayed

    Examples: 
      | username          | password |
      | thanh@auto.com    |   123321 |
      | email@invalid.com |   123123 |

  Scenario: TC_05_Login_successfully_with_an_existing_account
    Given I logins with an existing account
      | username            | password |
      | user@phptravels.com | demouser |
    And I choose the LOGIN button
    Then the system will navigate to the page "http://www.phptravels.net/account/"
    And the welcome message "Hi, John Smith" is showing
