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
import pageObjects.jQuery.FileUploadPageObject;

public class FW_09_File_Upload extends BaseTest {
	private WebDriver driver;
	private FileUploadPageObject fileUploadPage;

	private String javaFile = "java.jpg";
	private String cSharpFile = "cSharp.jpg";
	private String javaScriptFile = "javaScript.jpg";
	private String rubyFile = "ruby.jpg";
	private String pythonFile = "python.jpg";
	private String[] multipleFiles = { javaFile, cSharpFile, javaScriptFile, rubyFile, pythonFile };

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = createWebDriverAndNavigate(browserName, GlobalConstants.FILE_UPLOAD_URL);
		fileUploadPage = PageGeneratorJQuery.getFileUploadPage(driver);
	}

	@Test
	public void TC_01_Single_File() {
		fileUploadPage.uploadFileToPage(javaFile);

		Assert.assertTrue(fileUploadPage.isLoadedFileNameDisplayed(javaFile));

		fileUploadPage.clickStartButtonOfFile(javaFile);

		Assert.assertTrue(fileUploadPage.isUploadedFileLinkDisplayed(javaFile));

		Assert.assertTrue(fileUploadPage.isUploadedFileImageDisplayed(javaFile));

		fileUploadPage.clickDeleteButtonOfFile(javaFile);
	}

	@Test
	public void TC_02_Multiple_Files() {
		fileUploadPage.uploadFileToPage(multipleFiles);

		Assert.assertTrue(fileUploadPage.areLoadedFileNamesDisplayed(multipleFiles));

		fileUploadPage.clickAllStartButtonsOfFiles(multipleFiles);

		Assert.assertTrue(fileUploadPage.areUploadedFileLinksDisplayed(multipleFiles));

		Assert.assertTrue(fileUploadPage.areUploadedFileImagesDisplayed(multipleFiles));

		fileUploadPage.clickAllDeleteButtonsOfFiles(multipleFiles);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
