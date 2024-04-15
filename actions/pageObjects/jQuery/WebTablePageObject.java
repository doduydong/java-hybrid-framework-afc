package pageObjects.jQuery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.WebTablePageUI;

public class WebTablePageObject extends BasePage {
	private WebDriver driver;

	public WebTablePageObject(WebDriver driver) {
		this.driver = driver;
	}

	// CRUD Data Grid

	public void clickPaginationLinkByNumber(String pageNumber) {
		waitForElementClickable(driver, WebTablePageUI.PAGINATION_LINK_BY_NUMBER, pageNumber);
		clickElement(driver, WebTablePageUI.PAGINATION_LINK_BY_NUMBER, pageNumber);
	}

	public boolean isPaginationLinkActiveByNumber(String pageNumber) {
		waitForElementVisible(driver, WebTablePageUI.PAGINATION_LINK_BY_NUMBER, pageNumber);
		return getElementAttribute(driver, WebTablePageUI.PAGINATION_LINK_BY_NUMBER, "class", pageNumber).contains("active");
	}

	public void sendKeysToFilterTextboxByLabel(String columnLabel, String keysToSend) {
		waitForElementVisible(driver, WebTablePageUI.FILTER_TEXTBOX_BY_COLUMN_LABEL, columnLabel);
		sendKeysToElement(driver, WebTablePageUI.FILTER_TEXTBOX_BY_COLUMN_LABEL, keysToSend, columnLabel);
		pressKeyOnElement(driver, WebTablePageUI.FILTER_TEXTBOX_BY_COLUMN_LABEL, Keys.ENTER, columnLabel);
	}

	public boolean isFilteredRowDisplayedByData(String females, String country, String males, String total) {
		waitForElementVisible(driver, WebTablePageUI.FILTERED_ROW_BY_DATA, females, country, males, total);
		return isElementDisplayed(driver, WebTablePageUI.FILTERED_ROW_BY_DATA, females, country, males, total);
	}

	public List<String> getAllDataOfColumnByLabel(String columnLabel) {
		List<String> allDataOfColumn = new ArrayList<String>();
		int columnIndex = getNumberOfElements(driver, WebTablePageUI.COLUMN_INDEX_BY_LABEL, columnLabel) + 1;
		int numberOfPaginationLinks = getNumberOfElements(driver, WebTablePageUI.PAGINATION_LINKS);
		for (int i = 1; i <= numberOfPaginationLinks; i++) {
			clickPaginationLinkByNumber(String.valueOf(i));
			List<WebElement> dataRows = getListWebElement(driver, WebTablePageUI.DATA_ROWS_OF_COLUMN_BY_INDEX, String.valueOf(columnIndex));
			for (WebElement dataRow : dataRows) {
				allDataOfColumn.add(dataRow.getText());
			}
		}
		return allDataOfColumn;
	}

	// Dynamic Data Grid

	public void sendKeysToTextboxByHeaderLabelAtRow(String headerLabel, String rowNumber, String keysToSend) {
		int columnIndex = getNumberOfElements(driver, WebTablePageUI.COLUMN_INDEX_BY_HEADER_LABEL, headerLabel) + 1;
		waitForElementVisible(driver, WebTablePageUI.TEXTBOX_BY_ROW_AND_COLUMN_INDEX, rowNumber, String.valueOf(columnIndex));
		sendKeysToElement(driver, WebTablePageUI.TEXTBOX_BY_ROW_AND_COLUMN_INDEX, keysToSend, rowNumber, String.valueOf(columnIndex));
	}

	public void selectCountryDropdownAtRow(String rowNumber, String optionText) {
		waitForElementClickable(driver, WebTablePageUI.COUNTRY_DROPDOWN_BY_ROW, rowNumber);
		selectOptionInDefaultDropdown(driver, WebTablePageUI.COUNTRY_DROPDOWN_BY_ROW, optionText, rowNumber);
	}

	public void checkNPOCheckboxAtRow(String rowNumber) {
		waitForElementClickable(driver, WebTablePageUI.NPO_CHECKBOX_BY_ROW, rowNumber);
		checkDefaultCheckboxOrRadioButton(driver, WebTablePageUI.NPO_CHECKBOX_BY_ROW, rowNumber);
	}

	public void uncheckNPOCheckboxAtRow(String rowNumber) {
		waitForElementClickable(driver, WebTablePageUI.NPO_CHECKBOX_BY_ROW, rowNumber);
		uncheckDefaultCheckbox(driver, WebTablePageUI.NPO_CHECKBOX_BY_ROW, rowNumber);
	}

	public void setDateToMemberSinceDatePickerAtRow(String rowNumber, String dateData) {
		waitForElementVisible(driver, WebTablePageUI.DATE_MEMBER_SINCE_BY_ROW, rowNumber);
		removeElementAttributeByJS(driver, WebTablePageUI.DATE_MEMBER_SINCE_BY_ROW, "type", rowNumber);
		sendKeysToElement(driver, WebTablePageUI.DATE_MEMBER_SINCE_BY_ROW, dateData, rowNumber);
	}

	public void clickRowActionButtonAtRow(String rowNumber, String btnTitle) {
		waitForElementClickable(driver, WebTablePageUI.ACTION_BUTTON_BY_ROW_AND_TITLE, rowNumber, btnTitle);
		clickElement(driver, WebTablePageUI.ACTION_BUTTON_BY_ROW_AND_TITLE, rowNumber, btnTitle);
	}

	public void clickLoadDataButton() {
		waitForElementClickable(driver, WebTablePageUI.LOAD_DATA_BUTTON);
		clickElement(driver, WebTablePageUI.LOAD_DATA_BUTTON);
	}

	public void clickAppendRowButton() {
		waitForElementClickable(driver, WebTablePageUI.APPEND_ROW_BUTTON);
		clickElement(driver, WebTablePageUI.APPEND_ROW_BUTTON);
	}

	public void clickRemoveLastRowButton() {
		waitForElementClickable(driver, WebTablePageUI.REMOVE_LAST_ROW_BUTTON);
		clickElement(driver, WebTablePageUI.REMOVE_LAST_ROW_BUTTON);
	}

	public int getNumberOfDataRows() {
		waitForAllElementsVisible(driver, WebTablePageUI.DATA_ROWS);
		return getNumberOfElements(driver, WebTablePageUI.DATA_ROWS);
	}

}
