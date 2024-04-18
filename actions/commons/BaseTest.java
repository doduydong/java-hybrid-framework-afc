package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;

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
			TestFailuresManager.getTestFailures().addFailedResults(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return verifyResult;
	}

}
