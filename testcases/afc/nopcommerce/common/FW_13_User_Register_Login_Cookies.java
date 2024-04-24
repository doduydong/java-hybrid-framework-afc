package afc.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorNopCommerce;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class FW_13_User_Register_Login_Cookies extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	public static String firstName, lastName, emailAddress, password, company, date, month, year;
	public static Set<Cookie> loggedInCookies;

	@Parameters({ "browser", "url" })
	@BeforeTest
	public void beforeTest(String browserName, String pageUrl) {
		driver = createWebDriverAndNavigate(browserName, pageUrl);
		homePage = PageGeneratorNopCommerce.getHomePage(driver);

		firstName = "Dong";
		lastName = "Do";
		emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
		password = "Selenium3@";
		company = "AFC";
		date = "13";
		month = "October";
		year = "1997";

		registerPage = (RegisterPageObject) homePage.clickHeaderLinkByLinkText("Register");

		registerPage.checkRadioButtonByLabel("Male");

		registerPage.sendKeysToTextboxByID("FirstName", firstName);

		registerPage.sendKeysToTextboxByID("LastName", lastName);

		registerPage.selectDropdownByName("DateOfBirthDay", date);

		registerPage.selectDropdownByName("DateOfBirthMonth", month);

		registerPage.selectDropdownByName("DateOfBirthYear", year);

		registerPage.sendKeysToTextboxByID("Email", emailAddress);

		registerPage.sendKeysToTextboxByID("Company", company);

		registerPage.checkCheckboxByID("Newsletter");

		registerPage.sendKeysToTextboxByID("Password", password);

		registerPage.sendKeysToTextboxByID("ConfirmPassword", password);

		registerPage.clickRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickContinueButton();

		loginPage = (LoginPageObject) homePage.clickHeaderLinkByLinkText("Log in");

		loginPage.sendKeysToTextboxByID("Email", emailAddress);

		loginPage.sendKeysToTextboxByID("Password", password);

		homePage = loginPage.clickLoginButton();

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("My account"));

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));

		loggedInCookies = homePage.getLoggedInCookies();

		driver.quit();
	}

}
