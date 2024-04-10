package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageFactory extends BasePageFactory {
	private WebDriver driver;

	public RegisterPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameTextbox;

	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameTextbox;

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;

	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;

	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextbox;

	@FindBy(xpath = "//input[@id='Company']")
	private WebElement companyTextbox;

	@FindBy(xpath = "//select[@name='DateOfBirthDay']")
	private WebElement dayDropdown;

	@FindBy(xpath = "//select[@name='DateOfBirthMonth']")
	private WebElement monthDropdown;

	@FindBy(xpath = "//select[@name='DateOfBirthYear']")
	private WebElement yearDropdown;

	@FindBy(xpath = "//input[@id='gender-male']")
	private WebElement maleRadioButton;

	@FindBy(xpath = "//input[@id='Newsletter']")
	private WebElement newsletterCheckbox;

	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement registerButton;

	@FindBy(xpath = "//a[contains(@class,'register-continue-button')]")
	private WebElement continueButton;

	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMessage;

	public void sendKeysToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendKeysToElement(driver, firstNameTextbox, firstName);
	}

	public void sendKeysToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendKeysToElement(driver, lastNameTextbox, lastName);
	}

	public void sendKeysToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendKeysToElement(driver, emailTextbox, emailAddress);
	}

	public void sendKeysToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeysToElement(driver, passwordTextbox, password);
	}

	public void sendKeysToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendKeysToElement(driver, confirmPasswordTextbox, password);
	}

	public void sendKeysToCompanyTextbox(String company) {
		waitForElementVisible(driver, companyTextbox);
		sendKeysToElement(driver, companyTextbox, company);
	}

	public void selectDayDropdown(String date) {
		waitForElementClickable(driver, dayDropdown);
		selectOptionInDefaultDropdown(driver, dayDropdown, date);
	}

	public void selectMonthDropdown(String month) {
		waitForElementClickable(driver, monthDropdown);
		selectOptionInDefaultDropdown(driver, monthDropdown, month);
	}

	public void selectYearDropdown(String year) {
		waitForElementClickable(driver, yearDropdown);
		selectOptionInDefaultDropdown(driver, yearDropdown, year);
	}

	public void checkMaleRadioButton() {
		waitForElementClickable(driver, maleRadioButton);
		checkDefaultCheckboxOrRadioButton(driver, maleRadioButton);
	}

	public void checkNewsletterCheckbox() {
		waitForElementClickable(driver, newsletterCheckbox);
		checkDefaultCheckboxOrRadioButton(driver, newsletterCheckbox);
	}

	public void clickRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickElement(driver, registerButton);
	}

	public void clickContinueButton() {
		waitForElementClickable(driver, continueButton);
		clickElement(driver, continueButton);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

}
