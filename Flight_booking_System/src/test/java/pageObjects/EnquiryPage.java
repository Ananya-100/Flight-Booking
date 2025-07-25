package pageObjects;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.messages.types.Duration;

public class EnquiryPage {


    private WebDriver driver;

    // Constructor
    public EnquiryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By emailField = By.id("email"); 
    private By nameField = By.id("name");   
    private By phoneField = By.id("phone");
    private By subjectField = By.id("subject");
    private By messageField = By.id("message"); 
    
    private By sendButton = By.id("submit");
    private By errorMessage = By.id("emailError"); 
    private By successMsg = By.id("success-msg");
    

    // Methods
    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void fillRequiredFields() {
        driver.findElement(nameField).sendKeys("Test User");
        driver.findElement(phoneField).sendKeys("1234567890");
        driver.findElement(subjectField).sendKeys("Demo subject");
        driver.findElement(messageField).sendKeys("This is a test message.");
    }

    public void clickSend(){
    	
        driver.findElement(sendButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        boolean exists = driver.findElements(errorMessage).size() > 0;
        boolean visible = exists && driver.findElement(errorMessage).isDisplayed();
        System.out.println("Error element exists: " + exists + ", visible: " + visible);
        return visible;
    }
    
    public boolean isSuccessMessageVisible() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofMillis(5));
		WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
		return success.isDisplayed();
    }

    public String getSuccessMessageText() {
        try {
            return driver.findElement(successMsg).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

}
