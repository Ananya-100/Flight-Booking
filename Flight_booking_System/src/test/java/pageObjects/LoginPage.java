package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    By usernameField = By.id("username");
    By passwordField = By.id("password");
    By captchaField = By.id("captcha");
    By captcha = By.id("code");
    By validateButton = By.id("captchaBtn");
    By loginButton = By.id("login-submit");
    By userErr = By.id("usernameErr");
    By passErr = By.id("passwordErr");
    By forgotPasswordLink = By.id("reset-password-link");
    By rememberMeCheckbox = By.id("remember_me");
    By forgotPasswordPageHeader = By.tagName("h1"); 

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void enterCaptcha() {
        driver.findElement(captchaField).sendKeys(driver.findElement(captcha).getText());
    }
    public void enterInvalidCaptcha() {
        driver.findElement(captchaField).sendKeys("-");
    }

    public void clickValidateButton() {
        driver.findElement(validateButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getUserErr() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(userErr));
        return driver.findElement(userErr).getText();
    }
    public String getPassErr() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passErr));
        return driver.findElement(passErr).getText();
    }

    public boolean isLoginSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Login Alert: " + alertText);
            boolean isSuccess = alertText.equalsIgnoreCase("Login Successful");
            alert.accept();
            return isSuccess;

        } catch (TimeoutException e) {
            System.err.println("Alert for login success did not appear.");
            return false;
        }
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return driver.findElement(forgotPasswordLink).isDisplayed();
    }

    public void enableRememberMeCheckbox() {
        WebElement checkbox = driver.findElement(rememberMeCheckbox);
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }
    
    public void handleRememberMeAlerts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
        // First alert: "Do you want to remember changes?"
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert1 = driver.switchTo().alert();
        System.out.println("First Alert: " + alert1.getText());
        alert1.accept();

        // Second alert: "Username and Password Saved Successfully!"
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        System.out.println("Second Alert: " + alert2.getText());
        alert2.accept();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    public boolean isOnResetPasswordPage() {
        try {
            String pageSource = driver.getPageSource().toLowerCase();

            // Detect common 404 indicators (optional)
            if (pageSource.contains("404 not found") || driver.getTitle().contains("404")) {
                System.out.println("Detected 404 error on the page.");
                return false;
            }

            // Wait for expected heading
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordPageHeader));
            return heading.getText().toLowerCase().contains("reset");

        } catch (Exception e) {
            return false;
        }
    }



}
