package afc.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class FW_02_Selenium_Wrapper extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, company, date, month, year;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

		firstName = "Dong";
		lastName = "Do";
		emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
		password = "Selenium3@";
		company = "AFC";
		date = "13";
		month = "October";
		year = "1997";
	}

	@Test
	public void User_01_Register() {
		clickElement(driver, "//a[@class='ico-register']");

		checkDefaultCheckboxOrRadioButton(driver, "//input[@id='gender-male']");

		sendKeysToElement(driver, "//input[@id='FirstName']", firstName);

		sendKeysToElement(driver, "//input[@id='LastName']", lastName);

		selectOptionInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", date);

		selectOptionInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", month);

		selectOptionInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", year);

		sendKeysToElement(driver, "//input[@id='Email']", emailAddress);

		sendKeysToElement(driver, "//input[@id='Company']", company);

		checkDefaultCheckboxOrRadioButton(driver, "//input[@id='Newsletter']");

		sendKeysToElement(driver, "//input[@id='Password']", password);

		sendKeysToElement(driver, "//input[@id='ConfirmPassword']", password);

		clickElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");

		clickElement(driver, "//a[contains(@class,'register-continue-button')]");
	}

	@Test
	public void User_02_Login() {
		clickElement(driver, "//a[@class='ico-login']");

		sendKeysToElement(driver, "//input[@id='Email']", emailAddress);

		sendKeysToElement(driver, "//input[@id='Password']", password);

		clickElement(driver, "//button[contains(@class,'login-button')]");

		Assert.assertTrue(isElementDisplayed(driver, "//a[@class='ico-account']"));

		Assert.assertTrue(isElementDisplayed(driver, "//a[@class='ico-logout']"));
	}

	@Test
	public void User_03_Customer_Info() {
		clickElement(driver, "//a[@class='ico-account']");

		Assert.assertTrue(isElementDisplayed(driver, "//a[@class='ico-logout']"));

		Assert.assertTrue(isElementSelected(driver, "//input[@id='gender-male']"));

		Assert.assertEquals(getElementAttribute(driver, "//input[@id='FirstName']", "value"), firstName);

		Assert.assertEquals(getElementAttribute(driver, "//input[@id='LastName']", "value"), lastName);

		Assert.assertEquals(getSelectedOptionTextInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']"), date);

		Assert.assertEquals(getSelectedOptionTextInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']"), month);

		Assert.assertEquals(getSelectedOptionTextInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']"), year);

		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Email']", "value"), emailAddress);

		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Company']", "value"), company);

		Assert.assertTrue(isElementSelected(driver, "//input[@id='Newsletter']"));
	}

	@Test
	public void User_04_Logout() {
		clickElement(driver, "//a[@class='ico-logout']");

		Assert.assertTrue(isElementDisplayed(driver, "//a[@class='ico-register']"));

		Assert.assertTrue(isElementDisplayed(driver, "//a[@class='ico-login']"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}

}
