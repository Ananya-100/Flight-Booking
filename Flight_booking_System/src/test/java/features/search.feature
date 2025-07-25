Feature: Flight Search Functionality

  As a user using the Flight Search page,
  I want to search by flight number, name and type
  So that I can view the correct airline details.

  Background:
    Given the application URL is accessible
    And I navigate to the Flight Search page

  Scenario: TC-UI-SEARCH-001 - Valid Flight Number
    When I enter the flight number "AC789"
    Then the system should display "SkyRider Express" in the results

  Scenario: TC-UI-SEARCH-002 - Invalid Flight Number
    When I enter the flight number "INVALID123"
    Then the system should not display any matching airline
  
  Scenario: TC-UI-SEARCH-003 - Valid Flight Name
    When I search by flight name "Unity Express"
    Then the system should display flight name result "Unity Express"

  Scenario: TC-UI-SEARCH-004 - Invalid Flight Name
    When I search by flight name "AirIndia"
    Then no flight name fields should be visible

  Scenario: TC-UI-SEARCH-005 - Valid Flight Type
    When I search by flight type "Direct Flight"
    Then the system should display flight type result "Direct Flight"

  Scenario: TC-UI-SEARCH-006 - Invalid Flight Type
    When I search by flight type "Indirect Flight"
    Then the system should show no flight type results