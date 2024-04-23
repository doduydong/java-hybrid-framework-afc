package afc.facebook.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorFacebook;
import pageObjects.facebook.LoginPageObject;

public class FW_13_Element_Undisplayed extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String pageUrl) {
		driver = createWebDriverAndNavigate(browserName, pageUrl);
		loginPage = PageGeneratorFacebook.getLoginPage(driver);
	}

	@Test
	public void TC_01_Displayed() {
		loginPage.clickToCreateNewAccountButton();

		loginPage.sendKeysToEmailTextbox("dongafc@gmail.com");

		Assert.assertTrue(loginPage.isReEnterEmailTextboxDisplayed());
	}

	@Test
	public void TC_02_Undisplayed_In_DOM() {
		loginPage.sendKeysToEmailTextbox("");

		Assert.assertTrue(loginPage.isReEnterEmailTextboxUndisplayed());
	}

	@Test
	public void TC_03_Undisplayed_Not_In_DOM() {
		loginPage.clickToCloseSignUpIcon();

		Assert.assertTrue(loginPage.isEmailTextboxUndisplayed());

		Assert.assertTrue(loginPage.isReEnterEmailTextboxUndisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
