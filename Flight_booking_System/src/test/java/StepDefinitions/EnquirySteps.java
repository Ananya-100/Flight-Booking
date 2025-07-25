package StepDefinitions;



import java.util.concurrent.TimeoutException;

import org.junit.Assert;

import Factory.BaseClass;
import pageObjects.EnquiryPage;
import io.cucumber.java.en.*;

public class EnquirySteps {

    EnquiryPage enquiryPage = new EnquiryPage(BaseClass.getDriver());

    @Given("the application URL is accessible")
    public void application_url_is_accessible() {
        // Already navigated in Hooks.java
        System.out.println("Application launched at: " + BaseClass.getDriver().getCurrentUrl());
    }

    @And("I navigate to the Enquiry page")
    public void i_navigate_to_enquiry_page() {
        // You may use this step to click navigation if required
        // For now, it's loaded by default in your Hooks
    	BaseClass.getDriver().get("https://webapps.tekstac.com/FlightBooking/contactus.html");
    }

    @When("I enter a valid email format like {string} in the {string} field")
    public void i_enter_valid_email_format(String email,String field) {
        enquiryPage.enterEmail(email);
    }

    @When("I enter an invalid email format like {string} in the {string} field")
    public void i_enter_invalid_email_format(String email, String field) {
        enquiryPage.enterEmail(email);
    }

    @And("I fill other required fields with valid data")
    public void i_fill_other_required_fields() {
        enquiryPage.fillRequiredFields();
    }

    @And("I click the {string} button")
    public void i_click_the_button(String buttonName) {
        enquiryPage.clickSend();
    }

    @Then("no error message should be displayed")
    public void no_error_message_should_be_displayed() {
        Assert.assertFalse("Error message was displayed for a valid email!", enquiryPage.isErrorMessageDisplayed());
    }

    @Then("an appropriate error message should be displayed with proper styling")
    public void appropriate_error_message_should_be_displayed() {
        Assert.assertTrue("Error message was not displayed for invalid email!", enquiryPage.isErrorMessageDisplayed());
        //System.out.println("Error: " + enquiryPage.getErrorMessageText());
    }

    @And("the form submission should be prevented")
    public void form_submission_should_be_prevented() {
        Assert.assertTrue("Form submitted even though email was invalid!", enquiryPage.isErrorMessageDisplayed());
    }

    @And("the form should be submitted successfully")
    public void form_should_be_submitted_successfully() throws TimeoutException {
        Assert.assertTrue("Form was not submitted successfully!", enquiryPage.isSuccessMessageVisible());
        System.out.println("Success Message: " + enquiryPage.getSuccessMessageText());
    }

}
