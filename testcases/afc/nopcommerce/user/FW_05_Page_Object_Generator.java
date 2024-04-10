package afc.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.PageGeneratorNopCommerce;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class FW_05_Page_Object_Generator {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, emailAddress, password, company, date, month, year;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorNopCommerce.getHomePage(driver);

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
		registerPage = homePage.clickRegisterHeaderLink();

		registerPage.checkMaleRadioButton();

		registerPage.sendKeysToFirstNameTextbox(firstName);

		registerPage.sendKeysToLastNameTextbox(lastName);

		registerPage.selectDayDropdown(date);

		registerPage.selectMonthDropdown(month);

		registerPage.selectYearDropdown(year);

		registerPage.sendKeysToEmailTextbox(emailAddress);

		registerPage.sendKeysToCompanyTextbox(company);

		registerPage.checkNewsletterCheckbox();

		registerPage.sendKeysToPasswordTextbox(password);

		registerPage.sendKeysToConfirmPasswordTextbox(password);

		registerPage.clickRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickContinueButton();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickLoginHeaderLink();

		loginPage.sendKeysToEmailTextbox(emailAddress);

		loginPage.sendKeysToPasswordTextbox(password);

		homePage = loginPage.clickLoginButton();

		Assert.assertTrue(homePage.isMyAccountHeaderLinkDisplayed());

		Assert.assertTrue(homePage.isLogoutHeaderLinkDisplayed());
	}

	@Test
	public void User_03_Customer_Info() {
		customerInfoPage = homePage.clickMyAccountHeaderLink();

		Assert.assertTrue(customerInfoPage.isLogoutHeaderLinkDisplayed());

		Assert.assertTrue(customerInfoPage.isMaleRadioButtonSelected());

		Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);

		Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

		Assert.assertEquals(customerInfoPage.getDayDropdownValue(), date);

		Assert.assertEquals(customerInfoPage.getMonthDropdownValue(), month);

		Assert.assertEquals(customerInfoPage.getYearDropdownValue(), year);

		Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);

		Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(), company);

		Assert.assertTrue(customerInfoPage.isNewsletterCheckboxSelected());
	}

	@Test
	public void User_04_Logout() {
		homePage = customerInfoPage.clickLogoutHeaderLink();

		Assert.assertTrue(homePage.isRegisterHeaderLinkDisplayed());

		Assert.assertTrue(homePage.isLoginHeaderLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}

}
