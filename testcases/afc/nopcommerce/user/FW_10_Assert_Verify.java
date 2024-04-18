package afc.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorNopCommerce;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;

//@Listeners(commons.TestFailuresListener.class)
public class FW_10_Assert_Verify extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, emailAddress, password, company, date, month, year;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String pageUrl) {
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

		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

		homePage = registerPage.clickContinueButton();
	}

	@Test
	public void User_02_Login() {
		loginPage = (LoginPageObject) homePage.clickHeaderLinkByLinkText("Log in");

		loginPage.sendKeysToTextboxByID("Email", emailAddress);

		loginPage.sendKeysToTextboxByID("Password", password);

		homePage = loginPage.clickLoginButton();

		verifyFalse(homePage.isHeaderLinkDisplayedByLinkText("My account"));

		verifyFalse(homePage.isHeaderLinkDisplayedByLinkText("Log out"));
	}

	@Test
	public void User_03_Customer_Info() {
		customerInfoPage = (CustomerInfoPageObject) homePage.clickHeaderLinkByLinkText("My account");

		verifyTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));

		verifyFalse(customerInfoPage.isRadioButtonSelectedByLabel("Male"));

		verifyEquals(customerInfoPage.getTextboxValueByID("FirstName"), firstName);

		verifyEquals(customerInfoPage.getTextboxValueByID("LastName"), lastName);

		verifyEquals(customerInfoPage.getDropdownValueByName("DateOfBirthDay"), date);

		verifyEquals(customerInfoPage.getDropdownValueByName("DateOfBirthMonth"), month);

		verifyEquals(customerInfoPage.getDropdownValueByName("DateOfBirthYear"), year);

		verifyEquals(customerInfoPage.getTextboxValueByID("Email"), emailAddress);

		verifyEquals(customerInfoPage.getTextboxValueByID("Company"), company);

		verifyFalse(customerInfoPage.isCheckboxSelectedByID("Newsletter"));
	}

	@Test
	public void User_04_Logout() {
		homePage = (HomePageObject) customerInfoPage.clickHeaderLinkByLinkText("Log out");

		verifyFalse(homePage.isHeaderLinkDisplayedByLinkText("Register"));

		verifyFalse(homePage.isHeaderLinkDisplayedByLinkText("Log in"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
