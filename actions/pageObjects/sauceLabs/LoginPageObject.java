package pageObjects.sauceLabs;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorSauceLabs;
import pageUIs.sauceLabs.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void sendKeysToUserNameTextbox(String userName) {
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);
	}

	public void sendKeysToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public ProductPageObject clickLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorSauceLabs.getProductPage(driver);
	}

}
