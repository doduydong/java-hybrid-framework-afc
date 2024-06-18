package afc.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import afc.nopcommerce.data.UserData;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorNopCommerce;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.RegisterPageObject;
import reportConfig.ExtentReportsListener;

public class FW_15_Manage_Test_Data extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, emailAddress, password, company, date, month, year;

	@Parameters({ "browser", "server" })
	@BeforeClass
	public void beforeClass(String browserName, String pageUrl) {
		driver = createWebDriverAndNavigate(browserName, GlobalConstants.DEMO_USER_NOPCOMMERCE_URL);
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
	public void User_01_Register(Method method) {
		ExtentReportsListener.startTest(method.getName(), "User_01_Register");
		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 01: Click 'Register' header link");
		registerPage = (RegisterPageObject) homePage.clickHeaderLinkByLinkText("Register");

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 02: Check 'Male' radio button");
		registerPage.checkRadioButtonByLabel("Male");

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 03: Enter '" + firstName + "' into 'FirstName' textbox");
		registerPage.sendKeysToTextboxByID("FirstName", firstName);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 04: Enter '" + lastName + "' into 'LastName' textbox");
		registerPage.sendKeysToTextboxByID("LastName", lastName);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 05: Select '" + date + "' from 'Day' dropdown");
		registerPage.selectDropdownByName("DateOfBirthDay", date);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 06: Select '" + month + "' from 'Month' dropdown");
		registerPage.selectDropdownByName("DateOfBirthMonth", month);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 07: Select '" + year + "' from 'Year' dropdown");
		registerPage.selectDropdownByName("DateOfBirthYear", year);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 08: Enter '" + emailAddress + "' into 'Email' textbox");
		registerPage.sendKeysToTextboxByID("Email", emailAddress);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 09: Enter '" + company + "' into 'Company' textbox");
		registerPage.sendKeysToTextboxByID("Company", company);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 10: Check 'Newsletter' checkbox");
		registerPage.checkCheckboxByID("Newsletter");

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 11: Enter '" + password + "' into 'Password' textbox");
		registerPage.sendKeysToTextboxByID("Password", password);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 12: Enter '" + password + "' into 'ConfirmPassword' textbox");
		registerPage.sendKeysToTextboxByID("ConfirmPassword", password);

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 13: Click 'Register' button");
		registerPage.clickRegisterButton();

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 14: Verify 'Your registration completed' message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		ExtentReportsListener.getTest().log(Status.INFO, "User_01_Register - Step 15: Click 'Continue' button");
		homePage = registerPage.clickContinueButton();
	}

	@Test
	public void User_02_Login(Method method) {
		ExtentReportsListener.startTest(method.getName(), "User_02_Login");
		ExtentReportsListener.getTest().log(Status.INFO, "User_02_Login - Step 01: Click 'Log in' header link");
		loginPage = (LoginPageObject) homePage.clickHeaderLinkByLinkText("Log in");

		ExtentReportsListener.getTest().log(Status.INFO, "User_02_Login - Step 02: Enter '" + emailAddress + "' into 'Email' textbox");
		loginPage.sendKeysToTextboxByID("Email", emailAddress);

		ExtentReportsListener.getTest().log(Status.INFO, "User_02_Login - Step 03: Enter '" + password + "' into 'Password' textbox");
		loginPage.sendKeysToTextboxByID("Password", password);

		ExtentReportsListener.getTest().log(Status.INFO, "User_02_Login - Step 04: Click 'Log in' button");
		homePage = loginPage.clickLoginButton();

		ExtentReportsListener.getTest().log(Status.INFO, "User_02_Login - Step 05: Verify 'My account' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("My account"));

		ExtentReportsListener.getTest().log(Status.INFO, "User_02_Login - Step 06: Verify 'Log out' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));
	}

	@Test
	public void User_03_Customer_Info(Method method) {
		ExtentReportsListener.startTest(method.getName(), "User_03_Customer_Info");
		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 01: Click 'My account' header link");
		customerInfoPage = (CustomerInfoPageObject) homePage.clickHeaderLinkByLinkText("My account");

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 02: Verify 'Log out' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log out"));

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 03: Verify 'Male' radio button is selected");
		Assert.assertTrue(customerInfoPage.isRadioButtonSelectedByLabel("Male"));

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 04: Verify value of 'FirstName' textbox is '" + firstName + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("FirstName"), firstName);

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 05: Verify value of 'LastName' textbox is '" + lastName + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("LastName"), lastName);

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 06: Verify value of 'Day' dropdown is '" + date + "'");
		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthDay"), date);

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 07: Verify value of 'Month' dropdown is '" + month + "'");
		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthMonth"), month);

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 08: Verify value of 'Year' dropdown is '" + year + "'");
		Assert.assertEquals(customerInfoPage.getDropdownValueByName("DateOfBirthYear"), year);

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 09: Verify value of 'Email' textbox is '" + emailAddress + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("Email"), emailAddress);

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 10: Verify value of 'Company' textbox is '" + company + "'");
		Assert.assertEquals(customerInfoPage.getTextboxValueByID("Company"), company);

		ExtentReportsListener.getTest().log(Status.INFO, "User_03_Customer_Info - Step 11: Verify 'Newsletter' checkbox is selected");
		Assert.assertTrue(customerInfoPage.isCheckboxSelectedByID("Newsletter"));
	}

	@Test
	public void User_04_Logout(Method method) {
		ExtentReportsListener.startTest(method.getName(), "User_04_Logout");
		ExtentReportsListener.getTest().log(Status.INFO, "User_04_Logout - Step 01: Click 'Log out' header link");
		homePage = (HomePageObject) customerInfoPage.clickHeaderLinkByLinkText("Log out");

		ExtentReportsListener.getTest().log(Status.INFO, "User_04_Logout - Step 02: Verify 'Register' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Register"));

		ExtentReportsListener.getTest().log(Status.INFO, "User_04_Logout - Step 03: Verify 'Log in' header link is displayed");
		Assert.assertTrue(homePage.isHeaderLinkDisplayedByLinkText("Log in"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeWebDriver();
	}

}
