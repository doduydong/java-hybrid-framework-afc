package afc.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import afc.nopcommerce.common.FW_13_User_Register_Login_Cookies;
import commons.BaseTest;
import commons.PageGeneratorNopCommerce;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;

public class FW_13_Handle_Cookies extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private CustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, emailAddress, company, date, month, year;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String pageUrl) {
		driver = createWebDriverAndNavigate(browserName, pageUrl);
		homePage = PageGeneratorNopCommerce.getHomePage(driver);

		firstName = FW_13_User_Register_Login_Cookies.firstName;
		lastName = FW_13_User_Register_Login_Cookies.lastName;
		emailAddress = FW_13_User_Register_Login_Cookies.emailAddress;
		company = FW_13_User_Register_Login_Cookies.company;
		date = FW_13_User_Register_Login_Cookies.date;
		month = FW_13_User_Register_Login_Cookies.month;
		year = FW_13_User_Register_Login_Cookies.year;
	}

	@Test
	public void User_01_Login() {
		loginPage = (LoginPageObject) homePage.clickHeaderLinkByLinkText("Log in");

		homePage = loginPage.loginByCookies(FW_13_User_Register_Login_Cookies.loggedInCookies);
	}

	@Test
	public void User_02_Customer_Info() {
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
