package stepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.MyAccountPage;
import pages.PageFactory;
import pages.SignUpPage;

public class Net_phptravels_RegisterSteps extends AbstractTest {
	WebDriver driver;
	SignUpPage signupPage;
	HomePage homePage;
	MyAccountPage myaccountPage;

	// test data
	private String valid_email = "thanhtruong_" + randomString(4) + "@auto.com";
	private String firstname = "Truong";
	private String lastname = "Thanh";
	private String validPassword = "123123";

	public Net_phptravels_RegisterSteps(Hooks hooks) {
		driver = hooks.driver;
	}

	@When("^I choose the SignUp option from My Account menu$")
	public void i_choose_the_SignUp_option_from_My_Account_menu() {
		homePage = PageFactory.getHomePage(driver);
		signupPage = homePage.openSignUpPage(driver);
	}

	@Given("^I only choose the 'Sign Up' button$")
	public void i_only_choose_the_Sign_Up_button() throws InterruptedException {
		signupPage.submitSignUp();
	}

	@Then("^The error messages will be displayed for required fields$")
	public void the_error_messages_will_be_displayed_for_required_fields() {
		assertTrue(signupPage.verifyErrorMesssageEmtpyFields());
	}

	@Given("^I input an valid email as \"(.*?)\" into \"(.*?)\" field$")
	public void i_input_an_valid_email_as_into_Email_field(String valueemail, String field) {
		signupPage.enterValueToField(field, valueemail);
	}

	@Given("^I input valid value into all remaining required fields_FirstName LastName Password$")
	public void i_input_valid_value_into_all_remaining_required_fields_FirstName_LastName_Password() {
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Password", validPassword);
		signupPage.enterValueToField("Confirm Password", validPassword);
	}

	@When("^I choose the 'Sign Up' button$")
	public void i_choose_the_Sign_Up_button() throws InterruptedException {
		myaccountPage = signupPage.submitSignUp();
	}

	@Then("^The error messages \"(.*?)\" will be displayed$")
	public void the_error_messages_will_be_displayed(String message) {
		assertEquals(signupPage.getErrorMessageField(), message);
	}

	@Given("^I input an invalid password \"(.*?)\" into \"(.*?)\" field$")
	public void i_input_an_invalid_password_into_Password_field(String pass, String field) {
		signupPage.enterValueToField(field, pass);
		signupPage.enterValueToField("Confirm Password", pass);
	}

	@Given("^I input valid value into all remaining required fields_FirstName LastName Email$")
	public void i_input_valid_value_into_all_remaining_required_fields_FirstName_LastName_Email() {
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Email", valid_email);
	}

	@Given("^I input an valid password \"(.*?)\" into the \"(.*?)\"$")
	public void i_input_an_valid_password_into_the(String value, String field) {
		signupPage.enterValueToField(field, value);
	}

	@Given("^I input another password \"(.*?)\" into the \"(.*?)\"$")
	public void i_input_another_password_into_the(String value, String field) {
		signupPage.enterValueToField(field, value);
	}

	@Given("^I Input valid value into all required fields$")
	public void i_Input_valid_value_into_all_required_fields() {
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Email", valid_email);
		signupPage.enterValueToField("Password", validPassword);
		signupPage.enterValueToField("Confirm Password", validPassword);
	}

	@Then("^The system will navigate to the url \"(.*?)\"$")
	public void the_system_will_navigate_to_the_url(String url) {
		System.out.println(myaccountPage.getURL());
		assertEquals(myaccountPage.getURL(), url);
	}

	@Then("^the Welcome Message \"(.*?)\" is displayed$")
	public void the_Welcome_Message_is_displayed(String message) {
		assertEquals(myaccountPage.getWelcomeMessage(), message);
	}

	@Given("^I Input an existing email as \"(.*?)\" into the \"(.*?)\" field$")
	public void i_Input_an_existing_email_as_into_the_field(String value, String field) {
		signupPage.enterValueToField(field, value);
	}
}
