package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class CustomerInfoPageFactory extends BasePageFactory {
	private WebDriver driver;

	public CustomerInfoPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutHeaderLink;

	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameTextbox;

	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameTextbox;

	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;

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

	public boolean isLogoutHeaderLinkDisplayed() {
		waitForElementVisible(driver, logoutHeaderLink);
		return isElementDisplayed(driver, logoutHeaderLink);
	}

	public void clickLogoutHeaderLink() {
		waitForElementClickable(driver, logoutHeaderLink);
		clickElement(driver, logoutHeaderLink);
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, firstNameTextbox);
		return getElementAttribute(driver, firstNameTextbox, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, lastNameTextbox);
		return getElementAttribute(driver, lastNameTextbox, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(driver, emailTextbox);
		return getElementAttribute(driver, emailTextbox, "value");
	}

	public String getCompanyTextboxValue() {
		waitForElementVisible(driver, companyTextbox);
		return getElementAttribute(driver, companyTextbox, "value");
	}

	public String getDayDropdownValue() {
		waitForElementVisible(driver, dayDropdown);
		return getSelectedOptionTextInDefaultDropdown(driver, dayDropdown);
	}

	public String getMonthDropdownValue() {
		waitForElementVisible(driver, monthDropdown);
		return getSelectedOptionTextInDefaultDropdown(driver, monthDropdown);
	}

	public String getYearDropdownValue() {
		waitForElementVisible(driver, yearDropdown);
		return getSelectedOptionTextInDefaultDropdown(driver, yearDropdown);
	}

	public boolean isMaleRadioButtonSelected() {
		waitForElementVisible(driver, maleRadioButton);
		return isElementSelected(driver, maleRadioButton);
	}

	public boolean isNewsletterCheckboxSelected() {
		waitForElementVisible(driver, newsletterCheckbox);
		return isElementSelected(driver, newsletterCheckbox);
	}

}
