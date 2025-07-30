package pageObjects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookingPage {

    WebDriver driver;

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "travelFrom") // adjust if actual ID is different
    public WebElement travelFrom;

    @FindBy(id = "travelTo")
    public WebElement travelTo;

    @FindBy(id = "departure")
    public WebElement departureDate;

    @FindBy(id = "selectclass")
    public WebElement travelClass;

    @FindBy(id = "name")
    public WebElement passengerName;

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "phone")
    public WebElement phoneNumber;

    @FindBy(id = "ticket-class-count")
    public WebElement passengerCount;

    @FindBy(id = "ticket-class-increase")
    public WebElement plusButton;

    @FindBy(id = "ticket-class-decrease")
    public WebElement minusButton;

    @FindBy(id = "book-now")
    public WebElement bookButton;

    @FindBy(id = "error-msg")
    public List<WebElement> errorMessages;

    @FindBy(id = "bookingconfirm")
    public WebElement successMessage;

    public void fillBookingForm(String from, String to, String date, String cls, String name, String mail, String phone, int count) {
        travelFrom.clear();
        travelFrom.sendKeys(from);
        travelTo.clear();
        travelTo.sendKeys(to);
        departureDate.sendKeys(date);
        travelClass.sendKeys(cls);
        passengerName.sendKeys(name);
        email.sendKeys(mail);
        phoneNumber.sendKeys(phone);
        for (int i = 0; i < count; i++) {
            plusButton.click();
        }
    }

    public void clickBookButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("handleButton('book-now');");
    }



    public boolean isSuccessDisplayed() {
        return successMessage.isDisplayed();
    }

    public String getErrorMessage() {
        return errorMessages.size() > 0 ? errorMessages.get(0).getText() : "";
    }
}
