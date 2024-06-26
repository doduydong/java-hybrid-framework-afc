package pageObjects.nopCommerce;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorNopCommerce;
import commons.PatternObjectsNopCommerce;
import pageUIs.nopCommerce.HomePageUI;

public class HomePageObject extends PatternObjectsNopCommerce {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
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

	public Set<Cookie> getLoggedInCookies() {
		return getCookies(driver);
	}

}
