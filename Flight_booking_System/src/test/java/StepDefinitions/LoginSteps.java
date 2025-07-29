package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import io.cucumber.java.en.*;
import pageObjects.LoginPage;
import Factory.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginSteps {

    WebDriver driver = BaseClass.getDriver();
    LoginPage login = new LoginPage(driver);

    @Given("I open the browser")
    public void openBrowser() {
        // Already handled in Hooks.java
        System.out.println("Browser already opened in Hooks: " + driver.getCurrentUrl());
    }

    @And("I navigate to the login page")
    public void navigateToLogin() {
        driver.get("https://webapps.tekstac.com/FlightBooking/login.html");
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        login.enterUsername(username);
        login.enterPassword(password);
    }

    @And("I enter captcha")
    public void enterCaptcha() {
        login.enterCaptcha();
    }
    
    @And("I enter invalid captcha")
    public void enterInvalidCaptcha() {
        login.enterInvalidCaptcha();
    }

    @And("I click the {string} button on login page")
    public void clickButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Validate")) {
            login.clickValidateButton();
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        } else if (buttonName.equalsIgnoreCase("Login")) {
            login.clickLoginButton();
        } else {
            Assert.fail("Unknown button: " + buttonName);
        }
    }

    @Then("I should see a message {string}")
    public void shouldSeeMessage(String expectedMessage) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert message: " + alertText);
            Assert.assertEquals("Alert message mismatch", expectedMessage, alertText);
            alert.accept();

        } catch (Exception e) {
            System.out.println("No alert, checking message element...");
            try {
                By messageLocator = By.id("messageDisplay");
                WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(messageLocator));
                Assert.assertEquals("Page message mismatch", expectedMessage, message.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
                Assert.fail("No alert or visible message found.");
            }
        }
    }

    @Then("I should see an error message {string}")
    public void shouldSeeErrorMessage(String expectedErrorMessage) {
    	if (expectedErrorMessage.equals("Username is wrong")) {
    		Assert.assertEquals("Error message mismatch: ", expectedErrorMessage, login.getUserErr());
    	}
    	if (expectedErrorMessage.equals("Password is wrong")) {
    		Assert.assertEquals("Error message mismatch: ", expectedErrorMessage, login.getPassErr());
    	}
    }

    @And("I should be logged in successfully")
    public void shouldBeLoggedInSuccessfully() {
        Assert.assertTrue("Login was not successful.", login.isLoginSuccessful());
    }

    @Then("I should see the {string} link")
    public void shouldSeeLink(String linkText) {
        if (linkText.equalsIgnoreCase("Forgot Password")) {
            Assert.assertTrue("Forgot Password link is not displayed.", login.isForgotPasswordLinkDisplayed());
        } else {
            Assert.fail("Unknown link text: " + linkText);
        }
    }

    @And("I enable {string} checkbox")
    public void enableCheckbox(String checkboxText) {
        if (checkboxText.equalsIgnoreCase("Remember me on this computer")) {
            login.enableRememberMeCheckbox();
        } else {
            Assert.fail("Unknown checkbox: " + checkboxText);
        }
    }
    
    @And("I accept the remember me alerts")
    public void handleRememberMeAlerts() {
        login.handleRememberMeAlerts();
    }

    @And("I navigate to the login page again")
    public void navigateToLoginAgain() {
        driver.get("https://webapps.tekstac.com/FlightBooking/login.html");
    }

    
    @When("I click the {string} link")
    public void iClickTheLink(String linkText) {
        if (linkText.equalsIgnoreCase("Forgot Password")) {
            login.clickForgotPasswordLink();
        } else {
            Assert.fail("Unknown link: " + linkText);
        }
    }

    @Then("I should be redirected to the password reset page")
    public void iShouldBeRedirectedToResetPage() {
        Assert.assertTrue("Did not navigate to reset password page.", login.isOnResetPasswordPage());
    }

}


