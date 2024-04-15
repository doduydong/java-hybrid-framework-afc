package afc.jquery.user;

import java.util.List;

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

public class FW_08_CRUD_Data_Grid extends BaseTest {
	private WebDriver driver;
	private WebTablePageObject webTablePage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = createWebDriverAndNavigate(browserName, GlobalConstants.CRUD_DATA_GRID_URL);
		webTablePage = PageGeneratorJQuery.getWebTablePage(driver);
	}

	@Test
	public void TC_01_Pagination_Filtering() {
		webTablePage.clickPaginationLinkByNumber("23");
		webTablePage.isPaginationLinkActiveByNumber("23");

		webTablePage.sendKeysToFilterTextboxByLabel("Country", "Vietnam");
		Assert.assertTrue(webTablePage.isFilteredRowDisplayedByData("642000", "Vietnam", "678000", "1320000"));

		webTablePage.clickPaginationLinkByNumber("11");
		Assert.assertTrue(webTablePage.isPaginationLinkActiveByNumber("11"));

		webTablePage.sendKeysToFilterTextboxByLabel("Country", "Japan");
		Assert.assertTrue(webTablePage.isFilteredRowDisplayedByData("581262", "Japan", "610166", "1191442"));
	}

	@Test
	public void TC_02_Get_Data() {
		List<String> allDataOfColumn = webTablePage.getAllDataOfColumnByLabel("Country");

		System.out.println(allDataOfColumn.toString());

		for (String data : allDataOfColumn) {
			System.out.println(data);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
