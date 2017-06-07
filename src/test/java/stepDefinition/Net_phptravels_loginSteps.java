package stepDefinition;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LogInPage;
import pages.MyAccountPage;
import pages.PageFactory;

public class Net_phptravels_loginSteps {
	WebDriver driver;
	HomePage homePage;
	LogInPage loginPage;
	MyAccountPage myaccountPage;

	public Net_phptravels_loginSteps(Hooks hooks) {
		driver = hooks.driver;
	}

	@When("^I choose the Login option from My Account menu$")
	public void i_choose_the_Login_option_from_My_Account_menu() {
		homePage = PageFactory.getHomePage(driver);
		loginPage = homePage.openLogInPage(driver);
	}

	@Given("^I choose the LOGIN button$")
	public void i_choose_the_LOGIN_button() throws InterruptedException {
		myaccountPage = loginPage.submitLogin();
		Thread.sleep(1000);
	}

	@Then("^An error message \"(.*?)\" will be displayed$")
	public void an_error_message_will_be_displayed(String message) {
		assertEquals(loginPage.getErrorMessage(), message);
	}

	@Given("^I only input an valid email as \"(.*?)\"$")
	public void i_only_input_an_valid_email_as(String value) {
		loginPage.enterValueToField("Email", value);
	}

	@Given("^I only input a password as \"(.*?)\"$")
	public void i_only_input_a_password_as(String value) {
		loginPage.enterValueToField("Password", value);
	}

	@Given("^I input an invalid account with user name (.+) and password (.+)$")
	public void i_input_an_invalid_account_with_user_name__password(String email, String password) {
		loginPage.enterValueToField("Email", email);
		loginPage.enterValueToField("Password", password);
	}

	@Given("^I logins with an existing account$")
	public void i_logins_with_an_existing_account(DataTable table) {
		loginPage.enterValueToField("Email", table);
		loginPage.enterValueToField("Password", table);
	}

	@Then("^the system will navigate to the page \"(.+)\"$")
	public void the_system_will_navigate_to_the_page_http_www_phptravels_net_account(String targeturl) {
		assertEquals(myaccountPage.getURL(), targeturl);
	}

	@Then("^the welcome message \"(.+)\" is showing$")
	public void the_welcome_message_Hi_is_showing(String message) {
		assertEquals(loginPage.getWelcomeMessage(), message);
	}

}
