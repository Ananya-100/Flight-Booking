Feature: Email Field Validation on Enquiry Page

  As a user filling out the Enquiry form,
  I want the email field to accept only valid email formats,
  So that I can ensure proper communication and prevent form submission with invalid emails.

  Background:
    Given the application URL is accessible
    And I navigate to the Enquiry page

  Scenario: TC-UI-ENQUIRY-001 - Valid Email Address
    When I enter a valid email format like "tu@gmail.com" in the "Email Address" field
    And I fill other required fields with valid data
    And I click the "SEND" button
    Then no error message should be displayed
    And the form should be submitted successfully

  Scenario: TC-UI-ENQUIRY-002 - Invalid Email Address
    When I enter an invalid email format like "ananyagmail" in the "Email Address" field
    And I fill other required fields with valid data
    And I click the "SEND" button
    Then an appropriate error message should be displayed with proper styling
    And the form submission should be prevented
