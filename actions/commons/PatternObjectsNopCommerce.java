package commons;

import org.openqa.selenium.WebDriver;

public class PatternObjectsNopCommerce extends BasePage {
	private WebDriver driver;

	public PatternObjectsNopCommerce(WebDriver driver) {
		this.driver = driver;
	}

	private static final String HEADER_LINK_BY_LINK_TEXT = "//div[@class='header-links']//a[text()='%s']";
	private static final String TEXTBOX_BY_ID = "//input[@id='%s']";
	private static final String DROPDOWN_BY_NAME = "//select[@name='%s']";
	private static final String RADIOBUTTON_BY_LABEL = "//label[text()='%s']/preceding-sibling::input[@type='radio']";
	private static final String CHECKBOX_BY_ID = "//input[@id='%s'and@type='checkbox']";

	public PatternObjectsNopCommerce clickHeaderLinkByLinkText(String linkText) {
		waitForElementClickable(driver, HEADER_LINK_BY_LINK_TEXT, linkText);
		clickElement(driver, HEADER_LINK_BY_LINK_TEXT, linkText);
		switch (linkText) {
		case "Register":
			return PageGeneratorNopCommerce.getRegisterPage(driver);
		case "Log in":
			return PageGeneratorNopCommerce.getLoginPage(driver);
		case "My account":
			return PageGeneratorNopCommerce.getCustomerInfoPage(driver);
		case "Log out":
			return PageGeneratorNopCommerce.getHomePage(driver);
		default:
			throw new RuntimeException("Link text is invalid.");
		}
	}

	public boolean isHeaderLinkDisplayedByLinkText(String linkText) {
		waitForElementVisible(driver, HEADER_LINK_BY_LINK_TEXT, linkText);
		return isElementDisplayed(driver, HEADER_LINK_BY_LINK_TEXT, linkText);
	}

	public void sendKeysToTextboxByID(String textboxID, String keysToSend) {
		waitForElementVisible(driver, TEXTBOX_BY_ID, textboxID);
		sendKeysToElement(driver, TEXTBOX_BY_ID, keysToSend, textboxID);
	}

	public String getTextboxValueByID(String textboxID) {
		waitForElementVisible(driver, TEXTBOX_BY_ID, textboxID);
		return getElementAttribute(driver, TEXTBOX_BY_ID, "value", textboxID);
	}

	public void selectDropdownByName(String dropdownName, String optionText) {
		waitForElementClickable(driver, DROPDOWN_BY_NAME, dropdownName);
		selectOptionInDefaultDropdown(driver, DROPDOWN_BY_NAME, optionText, dropdownName);
	}

	public String getDropdownValueByName(String dropdownName) {
		waitForElementVisible(driver, DROPDOWN_BY_NAME, dropdownName);
		return getSelectedOptionTextInDefaultDropdown(driver, DROPDOWN_BY_NAME, dropdownName);
	}

	public void checkRadioButtonByLabel(String radioBtnLabel) {
		waitForElementClickable(driver, RADIOBUTTON_BY_LABEL, radioBtnLabel);
		checkDefaultCheckboxOrRadioButton(driver, RADIOBUTTON_BY_LABEL, radioBtnLabel);
	}

	public boolean isRadioButtonSelectedByLabel(String radioBtnLabel) {
		waitForElementVisible(driver, RADIOBUTTON_BY_LABEL, radioBtnLabel);
		return isElementSelected(driver, RADIOBUTTON_BY_LABEL, radioBtnLabel);
	}

	public void checkCheckboxByID(String checkboxID) {
		waitForElementClickable(driver, CHECKBOX_BY_ID, checkboxID);
		checkDefaultCheckboxOrRadioButton(driver, CHECKBOX_BY_ID, checkboxID);
	}

	public boolean isCheckboxSelectedByID(String checkboxID) {
		waitForElementVisible(driver, CHECKBOX_BY_ID, checkboxID);
		return isElementSelected(driver, CHECKBOX_BY_ID, checkboxID);
	}

}
