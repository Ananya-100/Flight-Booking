package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.List;
import java.time.Duration;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "myInputnumber")
    private WebElement flightNumberInput;
    
    @FindBy(id = "myInputname")
    private WebElement flightNameInput;

    @FindBy(id = "myInputtype")
    private WebElement flightTypeInput;

    @FindBy(id = "myTable")
    private WebElement resultTable;
    
    @FindBy(xpath = "//table[@id='myTable']//tr[td]")
    private List<WebElement> visibleFlightRows;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterFlightNumber(String number) {
        wait.until(ExpectedConditions.visibilityOf(flightNumberInput));
        flightNumberInput.clear();
        flightNumberInput.sendKeys(number);
    }
    
    public void enterFlightName(String name) {
        wait.until(ExpectedConditions.visibilityOf(flightNameInput));
        flightNameInput.clear();
        flightNameInput.sendKeys(name);
    }

    public void enterFlightType(String type) {
        wait.until(ExpectedConditions.visibilityOf(flightTypeInput));
        flightTypeInput.clear();
        flightTypeInput.sendKeys(type);
    }

    public boolean isFlightDisplayed(String expectedName) {
        wait.until(ExpectedConditions.visibilityOf(resultTable));
        return resultTable.getText().contains(expectedName);
    }

    public boolean isFlightNotDisplayed(String expectedName) {
        wait.until(ExpectedConditions.visibilityOf(resultTable));
        return !resultTable.getText().contains(expectedName);
    }
    
    public boolean isFlightNameDisplayed(String name) {
        wait.until(ExpectedConditions.visibilityOf(resultTable));
        return resultTable.getText().contains(name);
    }

    public boolean isFlightTypeDisplayed(String type) {
        wait.until(ExpectedConditions.visibilityOf(resultTable));
        return resultTable.getText().contains(type);
    }
    
    public boolean areNoFlightNameResultsVisible() {
        wait.until(ExpectedConditions.visibilityOf(resultTable));
        return visibleFlightRows.stream().noneMatch(WebElement::isDisplayed);
    }
}