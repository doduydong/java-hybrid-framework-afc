package afc.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import afc.nopcommerce.data.UserData;
import commons.BaseTest;
import commons.PageGeneratorNopCommerce;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class FW_18_Multiple_Environments extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, emailAddress, password, company, date, month, year;

	@Parameters({ "browser", "server", "role" })
	@BeforeClass
	public void beforeClass(String browserName, String serverName, String roleName) {
		driver = createWebDriverAndNavigateByServerAndRole(browserName, serverName, roleName);
		homePage = PageGeneratorNopCommerce.getHomePage(driver);

		firstName = UserData.UserInfo.FIRSTNAME;
		lastName = UserData.UserInfo.LASTNAME;
		emailAddress = UserData.UserInfo.EMAIL_USERNAME + getRandomNumber() + UserData.UserInfo.EMAIL_DOMAIN;
		password = UserData.UserInfo.PASSWORD;
		company = UserData.UserInfo.COMPANY;
		date = UserData.UserInfo.DOB_DAY;
		month = UserData.UserInfo.DOB_MONTH;
		year = UserData.UserInfo.DOB_YEAR;
	}

	@Test
	public void User_01_Register() {
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
	}

	@Test
	public void User_02_Login() {
		loginPage = (LoginPageObject) homePage.clickHeaderLinkByLinkText("Log in");

		loginPage.sendKeysToTextboxByID("Email", emailAddress);

		loginPage.sendKeysToTextboxByID("Password", password);

		homePage = loginPage.clickLoginButton();

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("My account"));

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));
	}

	@Test
	public void User_03_Customer_Info() {
		customerInfoPage = (CustomerInfoPageObject) homePage.clickHeaderLinkByLinkText("My account");

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));

		Assert.assertTrue(customerInfoPage.isRadioButtonSelectedByLabel("Male"));

		Assert.assertEquals(customerInfoPage.getTextboxValueByID("FirstName"), firstName);

		Assert.assertEquals(customerInfoPage.getTextboxValueByID("LastName"), lastName);

		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthDay"), date);

		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthMonth"), month);

		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthYear"), year);

		Assert.assertEquals(customerInfoPage.getTextboxValueByID("Email"), emailAddress);

		Assert.assertEquals(customerInfoPage.getTextboxValueByID("Company"), company);

		Assert.assertTrue(customerInfoPage.isCheckboxSelectedByID("Newsletter"));
	}

	@Test
	public void User_04_Logout() {
		homePage = (HomePageObject) customerInfoPage.clickHeaderLinkByLinkText("Log out");

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Register"));

		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log in"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeWebDriver();
	}

}
