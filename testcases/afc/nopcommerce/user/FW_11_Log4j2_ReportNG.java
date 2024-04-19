package afc.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
public class FW_11_Log4j2_ReportNG extends BaseTest {
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
		log.info("User_01_Register - Step 01: Click 'Register' header link");
		registerPage = (RegisterPageObject) homePage.clickHeaderLinkByLinkText("Register");

		log.info("User_01_Register - Step 02: Check 'Male' radio button");
		registerPage.checkRadioButtonByLabel("Male");

		log.info("User_01_Register - Step 03: Enter '" + firstName + "' into 'FirstName' textbox");
		registerPage.sendKeysToTextboxByID("FirstName", firstName);

		log.info("User_01_Register - Step 04: Enter '" + lastName + "' into 'LastName' textbox");
		registerPage.sendKeysToTextboxByID("LastName", lastName);

		log.info("User_01_Register - Step 05: Select '" + date + "' from 'Day' dropdown");
		registerPage.selectDropdownByName("DateOfBirthDay", date);

		log.info("User_01_Register - Step 06: Select '" + month + "' from 'Month' dropdown");
		registerPage.selectDropdownByName("DateOfBirthMonth", month);

		log.info("User_01_Register - Step 07: Select '" + year + "' from 'Year' dropdown");
		registerPage.selectDropdownByName("DateOfBirthYear", year);

		log.info("User_01_Register - Step 08: Enter '" + emailAddress + "' into 'Email' textbox");
		registerPage.sendKeysToTextboxByID("Email", emailAddress);

		log.info("User_01_Register - Step 09: Enter '" + company + "' into 'Company' textbox");
		registerPage.sendKeysToTextboxByID("Company", company);

		log.info("User_01_Register - Step 10: Check 'Newsletter' checkbox");
		registerPage.checkCheckboxByID("Newsletter");

		log.info("User_01_Register - Step 11: Enter '" + password + "' into 'Password' textbox");
		registerPage.sendKeysToTextboxByID("Password", password);

		log.info("User_01_Register - Step 12: Enter '" + password + "' into 'ConfirmPassword' textbox");
		registerPage.sendKeysToTextboxByID("ConfirmPassword", password);

		log.info("User_01_Register - Step 13: Click 'Register' button");
		registerPage.clickRegisterButton();

		log.info("User_01_Register - Step 14: Verify 'Your registration completed' message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed.");

		log.info("User_01_Register - Step 15: Click 'Continue' button");
		homePage = registerPage.clickContinueButton();
	}

	@Test
	public void User_02_Login() {
		log.info("User_02_Login - Step 01: Click 'Log in' header link");
		loginPage = (LoginPageObject) homePage.clickHeaderLinkByLinkText("Log in");

		log.info("User_02_Login - Step 02: Enter '" + emailAddress + "' into 'Email' textbox");
		loginPage.sendKeysToTextboxByID("Email", emailAddress);

		log.info("User_02_Login - Step 03: Enter '" + password + "' into 'Password' textbox");
		loginPage.sendKeysToTextboxByID("Password", password);

		log.info("User_02_Login - Step 04: Click 'Log in' button");
		homePage = loginPage.clickLoginButton();

		log.info("User_02_Login - Step 05: Verify 'My account' header link is displayed");
		Assert.assertFalse(homePage.isHeaderLinkDisplayedByLinkText("My account"));

		log.info("User_02_Login - Step 06: Verify 'Log out' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));
	}

	@Test
	public void User_03_Customer_Info() {
		log.info("User_03_Customer_Info - Step 01: Click 'My account' header link");
		customerInfoPage = (CustomerInfoPageObject) homePage.clickHeaderLinkByLinkText("My account");

		log.info("User_03_Customer_Info - Step 02: Verify 'Log out' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));

		log.info("User_03_Customer_Info - Step 03: Verify 'Male' radio button is selected");
		Assert.assertTrue(customerInfoPage.isRadioButtonSelectedByLabel("Male"));

		log.info("User_03_Customer_Info - Step 04: Verify value of 'FirstName' textbox is '" + firstName + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("FirstName"), firstName);

		log.info("User_03_Customer_Info - Step 05: Verify value of 'LastName' textbox is '" + lastName + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("LastName"), lastName);

		log.info("User_03_Customer_Info - Step 06: Verify value of 'Day' dropdown is '" + date + "'");
		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthDay"), date);

		log.info("User_03_Customer_Info - Step 07: Verify value of 'Month' dropdown is '" + month + "'");
		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthMonth"), month);

		log.info("User_03_Customer_Info - Step 08: Verify value of 'Year' dropdown is '" + year + "'");
		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthYear"), year);

		log.info("User_03_Customer_Info - Step 09: Verify value of 'Email' textbox is '" + emailAddress + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("Email"), emailAddress);

		log.info("User_03_Customer_Info - Step 10: Verify value of 'Company' textbox is '" + company + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("Company"), company);

		log.info("User_03_Customer_Info - Step 11: Verify 'Newsletter' checkbox is selected");
		Assert.assertFalse(customerInfoPage.isCheckboxSelectedByID("Newsletter"));
	}

	@Test
	public void User_04_Logout() {
		log.info("User_04_Logout - Step 01: Click 'Log out' header link");
		homePage = (HomePageObject) customerInfoPage.clickHeaderLinkByLinkText("Log out");

		log.info("User_04_Logout - Step 02: Verify 'Register' header link is displayed");
		Assert.assertFalse(homePage.isHeaderLinkDisplayedByLinkText("Register"));

		log.info("User_04_Logout - Step 03: Verify 'Log in' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log in"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
