package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorNopCommerce;
import pageUIs.nopCommerce.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public CustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isLogoutHeaderLinkDisplayed() {
		waitForElementVisible(driver, CustomerInfoPageUI.LOGOUT_HEADER_LINK);
		return isElementDisplayed(driver, CustomerInfoPageUI.LOGOUT_HEADER_LINK);
	}

	public HomePageObject clickLogoutHeaderLink() {
		waitForElementClickable(driver, CustomerInfoPageUI.LOGOUT_HEADER_LINK);
		clickElement(driver, CustomerInfoPageUI.LOGOUT_HEADER_LINK);
		return PageGeneratorNopCommerce.getHomePage(driver);
	}

	public String getFirstNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.FIRSTNAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.FIRSTNAME_TEXTBOX, "value");
	}

	public String getLastNameTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.LASTNAME_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.LASTNAME_TEXTBOX, "value");
	}

	public String getEmailTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.EMAIL_TEXTBOX, "value");
	}

	public String getCompanyTextboxValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_TEXTBOX);
		return getElementAttribute(driver, CustomerInfoPageUI.COMPANY_TEXTBOX, "value");
	}

	public String getDayDropdownValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.DAY_DROPDOWN);
		return getSelectedOptionTextInDefaultDropdown(driver, CustomerInfoPageUI.DAY_DROPDOWN);
	}

	public String getMonthDropdownValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
		return getSelectedOptionTextInDefaultDropdown(driver, CustomerInfoPageUI.MONTH_DROPDOWN);
	}

	public String getYearDropdownValue() {
		waitForElementVisible(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
		return getSelectedOptionTextInDefaultDropdown(driver, CustomerInfoPageUI.YEAR_DROPDOWN);
	}

	public boolean isMaleRadioButtonSelected() {
		waitForElementVisible(driver, CustomerInfoPageUI.MALE_RADIOBUTTON);
		return isElementSelected(driver, CustomerInfoPageUI.MALE_RADIOBUTTON);
	}

	public boolean isNewsletterCheckboxSelected() {
		waitForElementVisible(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
		return isElementSelected(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}

}
