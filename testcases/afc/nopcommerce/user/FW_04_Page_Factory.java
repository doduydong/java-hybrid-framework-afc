package afc.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageFactory.nopCommerce.CustomerInfoPageFactory;
import pageFactory.nopCommerce.HomePageFactory;
import pageFactory.nopCommerce.LoginPageFactory;
import pageFactory.nopCommerce.RegisterPageFactory;

public class FW_04_Page_Factory {
	private WebDriver driver;
	private HomePageFactory homePage;
	private RegisterPageFactory registerPage;
	private LoginPageFactory loginPage;
	private CustomerInfoPageFactory customerInfoPage;
	private String projectPath = System.getProperty("user.dir");
	private String firstName, lastName, emailAddress, password, company, date, month, year;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
		homePage = new HomePageFactory(driver);

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
		homePage.clickRegisterHeaderLink();
		registerPage = new RegisterPageFactory(driver);

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

		registerPage.clickContinueButton();
		homePage = new HomePageFactory(driver);
	}

	@Test
	public void User_02_Login() {
		homePage.clickLoginHeaderLink();
		loginPage = new LoginPageFactory(driver);

		loginPage.sendKeysToEmailTextbox(emailAddress);

		loginPage.sendKeysToPasswordTextbox(password);

		loginPage.clickLoginButton();
		homePage = new HomePageFactory(driver);

		Assert.assertTrue(homePage.isMyAccountHeaderLinkDisplayed());

		Assert.assertTrue(homePage.isLogoutHeaderLinkDisplayed());
	}

	@Test
	public void User_03_Customer_Info() {
		homePage.clickMyAccountHeaderLink();
		customerInfoPage = new CustomerInfoPageFactory(driver);

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
		customerInfoPage.clickLogoutHeaderLink();
		homePage = new HomePageFactory(driver);

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
