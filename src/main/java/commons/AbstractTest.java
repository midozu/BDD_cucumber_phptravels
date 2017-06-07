package commons;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

public class AbstractTest {
	WebDriver driver;

/*	public WebDriver openBrowser(String browser, String url) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_PATH);
			System.out.println(Constants.CHROME_PATH);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", Constants.IE_PATH);
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			// System.setProperty("webdriver.firefox.marionette",
			// Constants.FIREFOX_PATH);
			driver = new FirefoxDriver();
		}
		else System.out.println("Browser is not defined");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}*/

	public void closeBrowser(WebDriver driver) throws Exception {

		try {
			driver.quit();
			System.gc();
			if (driver.toString().toLowerCase().contains("chrome")) {
				String cmd = "taskkill /IM chromedriver.exe /F";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				String cmd = "taskkill /IM IEDriverServer.exe /F";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String randomString(int number) {
		return RandomStringUtils.randomAlphabetic(number);
	}

}
