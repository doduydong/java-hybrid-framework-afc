package commons;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import commons.GlobalConstants.BrowsersList;
import commons.GlobalConstants.RolesList;
import commons.GlobalConstants.ServersList;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DataFaker;

public class BaseTest {
	private WebDriver driver;
	protected final Logger log = LogManager.getLogger(getClass());

	public WebDriver getDriver() {
		return driver;
	}

	protected WebDriver createWebDriverAndNavigate(String browserName, String pageUrl) {
		BrowsersList browser = BrowsersList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("'" + browserName.toUpperCase() + "' Browser is invalid.");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(pageUrl);

		log.info("URL: " + pageUrl);

		return driver;
	}

	protected WebDriver createWebDriverAndNavigateByServerAndRole(String browserName, String serverName, String roleName) {
		BrowsersList browser = BrowsersList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("'" + browserName.toUpperCase() + "' Browser is invalid.");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(getUrlByServer(serverName, roleName));
		return driver;
	}

	private String getUrlByServer(String serverName, String roleName) {
		ServersList server = ServersList.valueOf(serverName.toUpperCase());
		RolesList role = RolesList.valueOf(roleName.toUpperCase());

		Map<RolesList, Map<ServersList, String>> urls = new HashMap<>();

		Map<ServersList, String> userUrls = new HashMap<>();
		userUrls.put(ServersList.DEV, GlobalConstants.DEV_USER_NOPCOMMERCE_URL);
		userUrls.put(ServersList.TEST, GlobalConstants.TEST_USER_NOPCOMMERCE_URL);
		userUrls.put(ServersList.STAGING, GlobalConstants.STAGING_USER_NOPCOMMERCE_URL);
		userUrls.put(ServersList.DEMO, GlobalConstants.DEMO_USER_NOPCOMMERCE_URL);
		userUrls.put(ServersList.PROD, GlobalConstants.PROD_USER_NOPCOMMERCE_URL);
		urls.put(RolesList.USER, userUrls);

		Map<ServersList, String> adminUrls = new HashMap<>();
		adminUrls.put(ServersList.DEV, GlobalConstants.DEV_ADMIN_NOPCOMMERCE_URL);
		adminUrls.put(ServersList.TEST, GlobalConstants.TEST_ADMIN_NOPCOMMERCE_URL);
		adminUrls.put(ServersList.STAGING, GlobalConstants.STAGING_ADMIN_NOPCOMMERCE_URL);
		adminUrls.put(ServersList.DEMO, GlobalConstants.DEMO_ADMIN_NOPCOMMERCE_URL);
		adminUrls.put(ServersList.PROD, GlobalConstants.PROD_ADMIN_NOPCOMMERCE_URL);
		urls.put(RolesList.ADMIN, adminUrls);

		if (!urls.containsKey(role)) {
			throw new RuntimeException("Invalid role: '" + roleName.toUpperCase() + "'");
		}

		log.info("Role: " + roleName);

		Map<ServersList, String> roleUrls = urls.get(role);

		if (!roleUrls.containsKey(server)) {
			throw new RuntimeException("Invalid server: '" + serverName.toUpperCase() + "'");
		}

		log.info("Server: " + serverName);

		String url = roleUrls.get(server);

		log.info("URL: " + url);

		return url;
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
