package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorNopCommerce;
import pageUIs.nopCommerce.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRegisterHeaderLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.REGISTER_HEADER_LINK);
		return isElementDisplayed(driver, HomePageUI.REGISTER_HEADER_LINK);
	}

	public RegisterPageObject clickRegisterHeaderLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_HEADER_LINK);
		clickElement(driver, HomePageUI.REGISTER_HEADER_LINK);
		return PageGeneratorNopCommerce.getRegisterPage(driver);
	}

	public boolean isLoginHeaderLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.LOGIN_HEADER_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGIN_HEADER_LINK);
	}

	public LoginPageObject clickLoginHeaderLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_HEADER_LINK);
		clickElement(driver, HomePageUI.LOGIN_HEADER_LINK);
		return PageGeneratorNopCommerce.getLoginPage(driver);
	}

	public boolean isMyAccountHeaderLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
	}

	public CustomerInfoPageObject clickMyAccountHeaderLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
		clickElement(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
		return PageGeneratorNopCommerce.getCustomerInfoPage(driver);
	}

	public boolean isLogoutHeaderLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.LOGOUT_HEADER_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGOUT_HEADER_LINK);
	}

}
