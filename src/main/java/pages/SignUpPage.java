package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.SignUpPageUI;

public class SignUpPage extends AbstractPage {
	WebDriver driver;

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
	}

	public MyAccountPage submitSignUp() throws InterruptedException {
		clickToElement(driver, SignUpPageUI.SIGNUP_BUTTON);
		Thread.sleep(2000);
		return PageFactory.getMyAccountPage(driver);

	}

	public void enterValueToField(String locator, String value) {
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, locator, value);
	}

	// get text

	public String getErrorMesssage() {
		return getTextOfElement(driver, SignUpPageUI.MESSAGE);
	}

	public String getErrorMessageField() {
		return getErrorMesssage();
	}

	public boolean verifyErrorMesssageEmtpyFields() {
		try {
			return getErrorMesssage().contains(
					"The Email field is required.\nThe Password field is required.\nThe Password field is required.\nThe First name field is required.\nThe Last Name field is required.");
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isURLNavigate() {
		try {
			return getCurrentURL(driver).contains("http://www.phptravels.net/account/");
		} catch (Exception e) {
			return false;
		}
	}

	public void createAccount(String string, String string2) {
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Email", string);
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Password", string2);
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Confirm Password", "123123");
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "First Name", "Truong");
		typeToElement(driver, SignUpPageUI.DYNAMIC_FIELD, "Last Name", "Thanh");
		clickToElement(driver, SignUpPageUI.SIGNUP_BUTTON);
	}

	public MyAccountPage openMyAccountPage(WebDriver driver) {
		return PageFactory.getMyAccountPage(driver);
	}

}
