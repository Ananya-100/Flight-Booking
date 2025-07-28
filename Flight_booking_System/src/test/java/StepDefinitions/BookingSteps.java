package StepDefinitions;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.BookingPage;

import static org.junit.Assert.*;

public class BookingSteps {

    WebDriver driver;
    BookingPage page;

    @Given("User is logged in and navigates to the Flight Ticket Booking page")
    public void userNavigatesToBookingPage() {
        driver = new ChromeDriver();
        driver.get("http://example.com/flight-booking"); // update actual URL
        page = new BookingPage(driver);
    }

    @When("User enters {string} in Travel From field")
    public void enterTravelFrom(String from) {
        page.travelFrom.sendKeys(from);
    }

    @When("User enters {string} in Travel To field")
    public void enterTravelTo(String to) {
        page.travelTo.sendKeys(to);
    }

    @When("User enters {string} as Departure Date")
    public void enterDate(String date) {
        page.departureDate.sendKeys(date);
    }

    @When("User selects {string}")
    public void selectClass(String cls) {
        page.travelClass.sendKeys(cls);
    }

    @When("User enters {string} as Passenger Name")
    public void enterName(String name) {
        page.passengerName.sendKeys(name);
    }

    @When("User enters {string} as Email")
    public void enterEmail(String email) {
        page.email.sendKeys(email);
    }

    @When("User enters {string} as Phone Number")
    public void enterPhone(String phone) {
        page.phoneNumber.sendKeys(phone);
    }

    @When("User sets {string} Passenger")
    public void setPassenger(String count) {
        int n = Integer.parseInt(count);
        for (int i = 0; i < n; i++) page.plusButton.click();
    }

    @When("User clicks on Book Ticket")
    public void clickBook() {
        page.clickBookButton();
    }

    @Then("Booking should be successful")
    public void bookingSuccessful() {
        assertTrue("Success message not displayed", page.isSuccessDisplayed());
    }

    @Then("Error message {string} should be displayed")
    public void verifyError(String expected) {
        assertEquals(expected, page.getErrorMessage());
    }

    @When("fills all other fields with valid data")
    public void fillValidDetails() {
        page.fillBookingForm("London", "Delhi", "12/12/2025", "First Class", "Ananya", "ananya@gmail.com", "9876543210", 1);
    }

    @When("User books a ticket with valid data")
    public void bookValidTicket() {
        fillValidDetails();
        page.clickBookButton();
    }

    @When("User tries to book again with same details")
    public void bookDuplicateTicket() {
        page.clickBookButton();
    }

    @Then("Duplicate booking warning should be displayed")
    public void verifyDuplicateWarning() {
        String error = page.getErrorMessage();
        assertTrue(error.contains("duplicate") || error.contains("already booked"));
    }
}
