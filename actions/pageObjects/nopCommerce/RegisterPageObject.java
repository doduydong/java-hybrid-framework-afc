package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void sendKeysToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void sendKeysToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void sendKeysToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void sendKeysToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void sendKeysToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}

	public void sendKeysToCompanyTextbox(String company) {
		waitForElementVisible(driver, RegisterPageUI.COMPANY_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUI.COMPANY_TEXTBOX, company);
	}

	public void selectDayDropdown(String date) {
		waitForElementClickable(driver, RegisterPageUI.DAY_DROPDOWN);
		selectOptionInDefaultDropdown(driver, RegisterPageUI.DAY_DROPDOWN, date);
	}

	public void selectMonthDropdown(String month) {
		waitForElementClickable(driver, RegisterPageUI.MONTH_DROPDOWN);
		selectOptionInDefaultDropdown(driver, RegisterPageUI.MONTH_DROPDOWN, month);
	}

	public void selectYearDropdown(String year) {
		waitForElementClickable(driver, RegisterPageUI.YEAR_DROPDOWN);
		selectOptionInDefaultDropdown(driver, RegisterPageUI.YEAR_DROPDOWN, year);
	}

	public void checkMaleRadioButton() {
		waitForElementClickable(driver, RegisterPageUI.MALE_RADIOBUTTON);
		checkDefaultCheckboxOrRadioButton(driver, RegisterPageUI.MALE_RADIOBUTTON);
	}

	public void checkNewsletterCheckbox() {
		waitForElementClickable(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
		checkDefaultCheckboxOrRadioButton(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
	}

	public void clickRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public void clickContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickElement(driver, RegisterPageUI.CONTINUE_BUTTON);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

}
