package afc.jquery.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorJQuery;
import pageObjects.jQuery.WebTablePageObject;

public class FW_08_Dynamic_Data_Grid extends BaseTest {
	private WebDriver driver;
	private WebTablePageObject webTablePage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = createWebDriverAndNavigate(browserName, GlobalConstants.DYNAMIC_DATA_GRID_URL);
		webTablePage = PageGeneratorJQuery.getWebTablePage(driver);
	}

	@Test
	public void TC_01_Cell_Interaction() {
		webTablePage.sendKeysToTextboxByHeaderLabelAtRow("Company", "1", "AFC");

		webTablePage.sendKeysToTextboxByHeaderLabelAtRow("Contact Person", "2", "Dong");

		webTablePage.selectCountryDropdownAtRow("3", "Japan");

		webTablePage.checkNPOCheckboxAtRow("1");

		webTablePage.uncheckNPOCheckboxAtRow("1");

		webTablePage.sendKeysToTextboxByHeaderLabelAtRow("Order Placed", "2", "0123456789");

		webTablePage.setDateToMemberSinceDatePickerAtRow("3", "14042024");

		webTablePage.clickRowActionButtonAtRow("1", "Insert Row Above");

		webTablePage.clickRowActionButtonAtRow("2", "Remove Current Row");

		webTablePage.clickRowActionButtonAtRow("3", "Move Up");

		webTablePage.clickRowActionButtonAtRow("1", "Move Down");
	}

	@Test
	public void TC_01_Load_Data() {
		webTablePage.clickLoadDataButton();

		Assert.assertEquals(webTablePage.getNumberOfDataRows(), 8);

		webTablePage.clickAppendRowButton();

		Assert.assertEquals(webTablePage.getNumberOfDataRows(), 9);

		webTablePage.clickRemoveLastRowButton();

		Assert.assertEquals(webTablePage.getNumberOfDataRows(), 8);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
