package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public void sendKeysToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public boolean isReEnterEmailTextboxDisplayed() {
		waitForElementVisible(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
		return isElementDisplayed(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
	}

	public boolean isReEnterEmailTextboxUndisplayed() {
		waitForElementInvisible(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
		return isElementUndisplayed(driver, LoginPageUI.RE_ENTER_EMAIL_TEXTBOX);
	}

	public void clickToCloseSignUpIcon() {
		waitForElementClickable(driver, LoginPageUI.CLOSE_SIGN_UP_ICON);
		clickElement(driver, LoginPageUI.CLOSE_SIGN_UP_ICON);
	}

	public boolean isEmailTextboxUndisplayed() {
		waitForElementInvisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		return isElementUndisplayed(driver, LoginPageUI.EMAIL_TEXTBOX);
	}

}
