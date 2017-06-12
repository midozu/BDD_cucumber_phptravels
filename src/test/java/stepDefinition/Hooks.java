package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends EventFiringWebDriver {
	String appURL = "http://www.phptravels.net/en";

	private static final WebDriver driver = new FirefoxDriver();
	private static final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			driver.close();
		}
	};

	static {
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public Hooks() {
		super(driver);
	}

	@Override
	public void close() {
		if (Thread.currentThread() != CLOSE_THREAD) {
			throw new UnsupportedOperationException(
					"You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
		}
		super.close();
	}

	@Before
	public void deleteAllCookies() {
		manage().deleteAllCookies();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@After
	public void embedScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				scenario.write("Taked Screenshot in target/html!!");
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
	}
}