package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.jQuery.FileUploadPageObject;
import pageObjects.jQuery.WebTablePageObject;

public class PageGeneratorJQuery {

	public static WebTablePageObject getWebTablePage(WebDriver driver) {
		return new WebTablePageObject(driver);
	}

	public static FileUploadPageObject getFileUploadPage(WebDriver driver) {
		return new FileUploadPageObject(driver);
	}

}
