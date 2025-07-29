Feature: Login

  @Login
  Scenario: Successful Login with Valid Credentials (TC-UI-LOGIN-001)
    Given I open the browser
    And I navigate to the login page
    When I enter username "flightadmin" and password "flightadmin"
    And I enter captcha
    And I click the "Validate" button on login page
    Then I should see a message "Valid input"
    When I click the "Login" button on login page
    Then I should see a message "Login Successful"

  @Login
  Scenario: Unsuccessful Login with Invalid Username (TC-UI-LOGIN-002)
    Given I open the browser
    And I navigate to the login page
    When I enter username "flight" and password "flightadmin"
    And I enter captcha
    And I click the "Validate" button on login page
    Then I should see a message "Valid input"
    When I click the "Login" button on login page
    Then I should see an error message "Username is wrong"

  @Login
  Scenario: Unsuccessful Login with Invalid Password (TC-UI-LOGIN-003)
    Given I open the browser
    And I navigate to the login page
    When I enter username "flightadmin" and password "wrongpass"
    And I enter captcha
    And I click the "Validate" button on login page
    Then I should see a message "Valid input"
    When I click the "Login" button on login page
    Then I should see an error message "Password is wrong"

  @Login
  Scenario: Navigate to Forgot Password page (TC-UI-LOGIN-004)
	Given I open the browser
	And I navigate to the login page
	Then I should see the "Forgot Password" link
	When I click the "Forgot Password" link
	Then I should be redirected to the password reset page

  @Login
  Scenario: Invalid Captcha (TC-UI-LOGIN-005)
    Given I open the browser
    And I navigate to the login page
    When I enter username "flightadmin" and password "flightadmin"
    And I enter invalid captcha
    And I click the "Validate" button on login page
    Then I should see a message "invalid input"

  @Login
  Scenario: Remember Me On This Computer Checkbox (TC-UI-LOGIN-006)
	Given I open the browser
	And I navigate to the login page
	When I enter username "admin" and password "admin"
	And I enter captcha
	And I click the "Validate" button on login page
	Then I should see a message "Valid input"
	And I enable "Remember me on this computer" checkbox
	And I accept the remember me alerts
	And I click the "Login" button on login page
	And I navigate to the login page again
	And I click the "Login" button on login page
	Then I should be logged in successfully
