package pageObjects.jQuery;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalConstants;
import pageUIs.jQuery.FileUploadPageUI;

public class FileUploadPageObject extends BasePage {
	private WebDriver driver;

	public FileUploadPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void uploadFileToPage(String... fileNames) {
		uploadFile(driver, fileNames);
	}

	public boolean isLoadedFileNameDisplayed(String fileNames) {
		waitForElementVisible(driver, FileUploadPageUI.LOADED_FILE_NAME, fileNames);
		return isElementDisplayed(driver, FileUploadPageUI.LOADED_FILE_NAME, fileNames);
	}

	public boolean areLoadedFileNamesDisplayed(String... fileNames) {
		boolean status = true;
		for (String fileName : fileNames) {
			status = isLoadedFileNameDisplayed(fileName);
		}
		return status;
	}

	public void clickStartButtonOfFile(String fileNames) {
		waitForElementClickable(driver, FileUploadPageUI.START_BUTTON_OF_FILE, fileNames);
		clickElement(driver, FileUploadPageUI.START_BUTTON_OF_FILE, fileNames);
		sleepInSecond(GlobalConstants.ONE_SEC);
	}

	public void clickAllStartButtonsOfFiles(String... fileNames) {
		for (String fileName : fileNames) {
			clickStartButtonOfFile(fileName);
		}
	}

	public boolean isUploadedFileLinkDisplayed(String fileNames) {
		waitForElementVisible(driver, FileUploadPageUI.UPLOADED_FILE_LINK, fileNames);
		return isElementDisplayed(driver, FileUploadPageUI.UPLOADED_FILE_LINK, fileNames);
	}

	public boolean areUploadedFileLinksDisplayed(String... fileNames) {
		boolean status = true;
		for (String fileName : fileNames) {
			status = isUploadedFileLinkDisplayed(fileName);
		}
		return status;
	}

	public boolean isUploadedFileImageDisplayed(String fileNames) {
		waitForElementVisible(driver, FileUploadPageUI.UPLOADED_FILE_IMG, fileNames);
		return isImageLoadedByJS(driver, FileUploadPageUI.UPLOADED_FILE_IMG, fileNames);
	}

	public boolean areUploadedFileImagesDisplayed(String... fileNames) {
		boolean status = true;
		for (String fileName : fileNames) {
			status = isUploadedFileImageDisplayed(fileName);
		}
		return status;
	}

	public void clickDeleteButtonOfFile(String fileNames) {
		waitForElementClickable(driver, FileUploadPageUI.DELETE_BUTTON_OF_FILE, fileNames);
		clickElement(driver, FileUploadPageUI.DELETE_BUTTON_OF_FILE, fileNames);
		sleepInSecond(GlobalConstants.ONE_SEC);
	}

	public void clickAllDeleteButtonsOfFiles(String... fileNames) {
		for (String fileName : fileNames) {
			clickDeleteButtonOfFile(fileName);
		}
	}

}
