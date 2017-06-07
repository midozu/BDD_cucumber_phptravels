package stepDefinition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import commons.Constants;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	WebDriver driver;
	String appURL = "http://www.phptravels.net/en";

	@Before
	public WebDriver setupBrowser() {
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_PATH);
		driver = new ChromeDriver();
		System.out.println("Starting browser ......");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

	@After
	public void embedScreenshot(Scenario scenario) throws IOException, InterruptedException {
		if (scenario.isFailed()) {
			try {
				scenario.write("Taked Screenshot in target/html!!");
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
		try {
			driver.quit();
			System.gc();
			String cmd = "taskkill /F /IM chromedriver.exe";
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
