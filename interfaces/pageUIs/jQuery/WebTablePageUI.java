package pageUIs.jQuery;

public class WebTablePageUI {

	// CRUD Data Grid

	public static final String PAGINATION_LINK_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String FILTER_TEXTBOX_BY_COLUMN_LABEL = "//div[text()='%s']/parent::div/following-sibling::input";
	public static final String FILTERED_ROW_BY_DATA = "//tr/td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']";
	public static final String PAGINATION_LINKS = "//li[@class='qgrd-pagination-page']/a";
	public static final String COLUMN_INDEX_BY_LABEL = "//div[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String DATA_ROWS_OF_COLUMN_BY_INDEX = "//table/tbody/tr/td[%s]";

	// Dynamic Data Grid

	public static final String COLUMN_INDEX_BY_HEADER_LABEL = "//thead/tr//th[text()='%s']/preceding-sibling::th";
	public static final String TEXTBOX_BY_ROW_AND_COLUMN_INDEX = "//tr[%s]//td[%s]/input";
	public static final String COUNTRY_DROPDOWN_BY_ROW = "//tr[%s]//select";
	public static final String NPO_CHECKBOX_BY_ROW = "//tr[%s]//input[@type='checkbox']";
	public static final String DATE_MEMBER_SINCE_BY_ROW = "//tr[%s]//input[contains(@id,'memberSince')]";
	public static final String ACTION_BUTTON_BY_ROW_AND_TITLE = "//tr[%s]//button[@title='%s']";
	public static final String LOAD_DATA_BUTTON = "//button[@id='load']";
	public static final String APPEND_ROW_BUTTON = "//button[@title='Append Row']";
	public static final String REMOVE_LAST_ROW_BUTTON = "//button[@title='Remove Last Row']";
	public static final String DATA_ROWS = "//table/tbody/tr";

}
