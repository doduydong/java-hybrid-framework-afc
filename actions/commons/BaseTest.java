package commons;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DataFaker;

public class BaseTest {
	private WebDriver driver;
	protected final Logger log = LogManager.getLogger(getClass());

	public WebDriver getDriver() {
		return driver;
	}

	protected WebDriver createWebDriverAndNavigate(String browserName, String pageUrl) {
		switch (browserName) {
		case "firefox":
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case "chrome":
			driver = WebDriverManager.chromedriver().create();
			break;
		case "edge":
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("'" + browserName.toUpperCase() + "' Browser is invalid.");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(pageUrl);
		return driver;
	}

	protected int getRandomNumber() {
		return new Random().nextInt(99999);
	}

	protected boolean verifyTrue(boolean condition) {
		boolean verifyResult = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			verifyResult = false;
			log.info("---------------------- FAILED ----------------------");
			TestFailuresManager.getTestFailures().addFailedResults(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return verifyResult;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean verifyResult = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			verifyResult = false;
			log.info("---------------------- FAILED ----------------------");
			TestFailuresManager.getTestFailures().addFailedResults(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return verifyResult;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean verifyResult = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			verifyResult = false;
			log.info("---------------------- FAILED ----------------------");
			TestFailuresManager.getTestFailures().addFailedResults(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return verifyResult;
	}

	protected void closeWebDriver() {
		if (driver != null) {
			try {
				driver.manage().deleteAllCookies();
				driver.quit();
			} catch (Exception e) {
				log.error("Error while quitting WebDriver: " + e.getMessage());
			} finally {
				String osName = GlobalConstants.OS_NAME.toLowerCase();
				String browserInfo = driver.toString().toLowerCase();
				String browserDriver = null;
				String cmd = null;

				try {
					log.info("OS name: " + osName);
					log.info("Browser info: " + browserInfo);

					if (browserInfo.contains("firefox")) {
						browserDriver = "geckodriver";
					} else if (browserInfo.contains("chrome")) {
						browserDriver = "chromedriver";
					} else if (browserInfo.contains("edge")) {
						browserDriver = "msedgedriver";
					} else {
						browserDriver = "safaridriver";
					}

					log.info("Browser driver: " + browserDriver);

					if (osName.contains("window")) {
						cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriver + "*\"";
					} else {
						cmd = "pkill " + browserDriver;
					}

					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				} catch (IOException | InterruptedException e) {
					log.error("Error while closing browser process: " + e.getMessage());
				}
			}
		}
	}

	public DataFaker getDataFakerFrom(String locale) {
		log.info("Locale: " + locale);
		return new DataFaker(locale);
	}

}
