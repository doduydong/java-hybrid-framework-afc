package pageObjects.nopCommerce;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorNopCommerce;
import commons.PatternObjectsNopCommerce;
import pageUIs.nopCommerce.LoginPageUI;

public class LoginPageObject extends PatternObjectsNopCommerce {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void sendKeysToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void sendKeysToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public HomePageObject clickLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorNopCommerce.getHomePage(driver);
	}

	public HomePageObject loginByCookies(Set<Cookie> loggedInCookies) {
		addCookies(driver, loggedInCookies);
		refreshPage(driver);
		waitForElementClickable(driver, "//div[@class='bar-notification success']/span[@class='close']");
		clickElement(driver, "//div[@class='bar-notification success']/span[@class='close']");
		waitForElementInvisible(driver, "//div[@class='bar-notification success']/span[@class='close']");
		return PageGeneratorNopCommerce.getHomePage(driver);
	}

}
