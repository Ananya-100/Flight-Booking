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

    @And("I fill email field with valid data like {string}")
    public void i_enter_valid_email_format(String email) {
        enquiryPage.enterEmail(email);
    }

    @And("I fill email field with invalid data like {string}")
    public void i_enter_invalid_email_format(String email) {
        enquiryPage.enterEmail(email);
    }

    @And("I fill name field with valid data like {string}")
    public void name_field_data(String name) {
        enquiryPage.enterName(name);
//        enquiryPage.enterPhone("1234567890");
//        enquiryPage.enterSubject("This is test meassage");
//        enquiryPage.enterMsg("I have problem regarding the customeer service");
    }
    @And("I fill phone field with valid data like {string}")
    public void phone_field_data(String phno)
    {
    	enquiryPage.enterPhone(phno);
    }
    @And("I fill subject field with valid data like {string}")
    public void subject_field_data(String sub)
    {
    	enquiryPage.enterSubject(sub);
    }
    
    @And("I fill message field with valid data like {string}")
    public void msg_field_data(String msg)
    {
    	enquiryPage.enterMsg(msg);
    }
    
    @And("I fill name field with blank data like {string}")
    public void invalid_data(String s1) {
        enquiryPage.enterName(s1);
    }
    

    @And("I click the {string} button")
    public void i_click_the_button(String buttonName) throws InterruptedException {
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
    
    
    @Then("an error message should be displayed")
    public void name_error_msg() {
    	Assert.assertTrue("Error message for name was not displayed!", enquiryPage.getErrMsgName());

    }
    @And("the form submission should be prevented due to invalid name")
    public void form_submission_should_be_prevented_Name() {
        Assert.assertTrue("Form submitted even though email was invalid!", enquiryPage.getErrMsgName());
    }
    
    
    @When("I enter {string} in the Phone Number field")
    public void i_enter_invalid_phone_number(String phone) {
        enquiryPage.enterPhone(phone);
    }

    @Then("the form should not be submitted")
    public void form_should_not_be_submitted() {
        boolean isSuccessVisible;
        try {
            isSuccessVisible = enquiryPage.isSuccessMessageVisible();
        } catch (TimeoutException e) {
            isSuccessVisible = false;
        }
        Assert.assertFalse("Form was submitted even with invalid phone!", isSuccessVisible);
    }

    @Then("an error message should be displayed for the Phone Number field")
    public void error_message_should_be_displayed_for_phone() {
        Assert.assertTrue("Error message not displayed for phone field!", enquiryPage.getErrMsgPhone());
    }


}
