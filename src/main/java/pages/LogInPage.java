package pages;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import cucumber.api.DataTable;
import interfaces.LogInPageUI;
import interfaces.SignUpPageUI;

public class LogInPage extends AbstractPage {
	WebDriver driver;

	public LogInPage(WebDriver driver) {
		this.driver = driver;
	}

	public MyAccountPage submitLogin() {
		clickToElement(driver, LogInPageUI.LOGIN_BUTTON);
		return PageFactory.getMyAccountPage(driver);
	}

	public String getErrorMessage() {
		return getTextOfElement(driver, LogInPageUI.MESSAGE);
	}

	public void enterValueToField(String locator, String value) {
		typeToElement(driver, LogInPageUI.DYNAMIC_FIELD, locator, value);
	}

	public boolean isURLNavigate() {
		try {
			return getCurrentURL(driver).contains("http://www.phptravels.net/account/");
		} catch (Exception e) {
			return false;
		}
	}

	public String getWelcomeMessage() {
		return getTextOfElement(driver, SignUpPageUI.WELCOME_TXT);
	}

	public void enterValueToField(String locator, DataTable table) {
		for (Map<String, String> data : table.asMaps(String.class, String.class)) {
			if (locator == "Email")
				typeToElement(driver, LogInPageUI.DYNAMIC_FIELD, locator, data.get("username"));
			else
				typeToElement(driver, LogInPageUI.DYNAMIC_FIELD, locator, data.get("password"));
		}
	}

	public MyAccountPage openMyAccountPage(WebDriver driver) {
		return PageFactory.getMyAccountPage(driver);
	}

}
