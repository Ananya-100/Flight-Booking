Feature: Flight Ticket Booking Field Validations

  Background:
    Given the application is accessible
    And I navigate to the Booking page


  @Positive
  Scenario: TC-UI-TICKET-001 - Valid Travel From/To
    When User enters "London" in Travel From field
    And User enters "Mumbai" in Travel To field
    And User enters "12/07/2025" as Departure Date
    And User selects "First Class"
    And User enters "Ananya" as Passenger Name
    And User enters "ananya@gmail.com" as Email
    And User enters "9876543210" as Phone Number
    And User sets "1" Passenger
    And User clicks on Book Ticket
    Then Booking should be successful

  @Negative
  Scenario: TC-UI-TICKET-002 - Invalid Travel From/To
    When User enters "asdfghjk" in Travel From field
    And User enters "xyz" in Travel To field
    And fills all other fields with valid data
    And User clicks on Book Ticket
    Then Error message "Invalid City Name" should be displayed

  @Positive
  Scenario: TC-UI-TICKET-003 - Valid Date
    When User enters "12/07/2025" as Departure Date
    And fills all other fields with valid data
    And User clicks on Book Ticket
    Then Booking should be successful

  @Negative
  Scenario: TC-UI-TICKET-004 - Invalid Date
    When User enters "8888888" as Departure Date
    And fills all other fields with valid data
    And User clicks on Book Ticket
    Then Error message "Invalid/Past Date" should be displayed

  @Positive
  Scenario: TC-UI-TICKET-005 - Valid Email
    When User enters "ananya@gmail.com" as Email
    And fills all other fields with valid data
    And User clicks on Book Ticket
    Then Booking should be successful

  @Negative
  Scenario: TC-UI-TICKET-006 - Invalid Email
    When User enters "testexample.com" as Email
    And fills all other fields with valid data
    And User clicks on Book Ticket
    Then Error message "Invalid Email Format" should be displayed

  @Positive
  Scenario: TC-UI-TICKET-007 - Valid Phone Number
    When User enters "9876543210" as Phone Number
    And fills all other fields with valid data
    And User clicks on Book Ticket
    Then Booking should be successful

  @Negative
  Scenario: TC-UI-TICKET-008 - Invalid Phone Number
    When User enters "123" as Phone Number
    And fills all other fields with valid data
    And User clicks on Book Ticket
    Then Error message "Please enter a valid 10-digit phone number" should be displayed

  @Negative
  Scenario: TC-UI-TICKET-009 - Duplicate Booking Check
    When User books a ticket with valid data
    And User tries to book again with same details
    Then Duplicate booking warning should be displayed
