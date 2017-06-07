Feature: Validate for Sign up feature

  Background: Open the SignUP page
    #Given I am on the "http://www.phptravels.net/en" site
    Given I choose the SignUp option from My Account menu

  Scenario: TC_01_Register unsuccessful with all empty fields
    When I only choose the 'Sign Up' button
    Then The error messages will be displayed for required fields

  Scenario: TC_02_Register unsuccessful with an invalid email
    When I input an valid email as "abc@xyz" into "Email" field
    And I input valid value into all remaining required fields_FirstName LastName Password
    And I choose the 'Sign Up' button
    Then The error messages "The Email field must contain a valid email address." will be displayed

  Scenario: TC_03_Register unsuccessful with an invalid password
    When I input an invalid password "123" into "Password" field
    And I input valid value into all remaining required fields_FirstName LastName Email
    And I choose the 'Sign Up' button
    Then The error messages "The Password field must be at least 6 characters in length." will be displayed

  Scenario: TC_04_Register unccessful with Password and Confirm Password not match
    When I input an valid password "123123" into the "Password"
    And I input another password "123456" into the "Confirm Password"
    And I input valid value into all remaining required fields_FirstName LastName Email
    And I choose the 'Sign Up' button
    Then The error messages "Password not matching with confirm password." will be displayed

  Scenario: TC_05_Register successfully when I input valid value into all requied fields
    Given I Input valid value into all required fields
    When I choose the 'Sign Up' button
    Then The system will navigate to the url "http://www.phptravels.net/account/"
    And the Welcome Message "Hi, Truong Thanh" is displayed

  Scenario: TC_06_Register unsuccessful with an existing email
    Given I Input an existing email as "user@phptravels.com" into the "Email" field
    And I input valid value into all remaining required fields_FirstName LastName Password
    When I choose the 'Sign Up' button
    Then The error messages "Email Already Exists." will be displayed
