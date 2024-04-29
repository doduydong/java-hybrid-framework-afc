package afc.saucelabs.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorSauceLabs;
import pageObjects.sauceLabs.LoginPageObject;
import pageObjects.sauceLabs.ProductPageObject;

public class FW_15_Data_Sorting extends BaseTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private ProductPageObject productPage;
	private String userName, password;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String pageUrl) {
		driver = createWebDriverAndNavigate(browserName, pageUrl);
		loginPage = PageGeneratorSauceLabs.getLoginPage(driver);

		userName = "standard_user";
		password = "secret_sauce";
	}

	@Test
	public void Sort_01_Login_As_User() {
		loginPage.sendKeysToUserNameTextbox(userName);

		loginPage.sendKeysToPasswordTextbox(password);

		productPage = loginPage.clickLoginButton();

		Assert.assertTrue(productPage.isHeaderLabelDisplayed());

		Assert.assertEquals(productPage.verifyNumberOfDisplayedProducts(), 6);
	}

	@Test
	public void Sort_02_Product_Names() {
		productPage.sortProducts("Name (A to Z)");

		Assert.assertTrue(productPage.isProductNamesSortedAscending());

		productPage.sortProducts("Name (Z to A)");

		Assert.assertTrue(productPage.isProductNamesSortedDescending());

	}

	@Test
	public void Sort_03_Product_Prices() {
		productPage.sortProducts("Price (low to high)");

		Assert.assertTrue(productPage.isProductPricesSortedAscending());

		productPage.sortProducts("Price (high to low)");

		Assert.assertTrue(productPage.isProductPricesSortedDescending());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeWebDriver();
	}

}
