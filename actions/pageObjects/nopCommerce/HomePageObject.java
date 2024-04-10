package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
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

	public void clickRegisterHeaderLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_HEADER_LINK);
		clickElement(driver, HomePageUI.REGISTER_HEADER_LINK);
	}

	public boolean isLoginHeaderLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.LOGIN_HEADER_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGIN_HEADER_LINK);
	}

	public void clickLoginHeaderLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_HEADER_LINK);
		clickElement(driver, HomePageUI.LOGIN_HEADER_LINK);
	}

	public boolean isMyAccountHeaderLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
	}

	public void clickMyAccountHeaderLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
		clickElement(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
	}

	public boolean isLogoutHeaderLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.LOGOUT_HEADER_LINK);
		return isElementDisplayed(driver, HomePageUI.LOGOUT_HEADER_LINK);
	}

}
