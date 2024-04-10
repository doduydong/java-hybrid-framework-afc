package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class HomePageFactory extends BasePageFactory {
	private WebDriver driver;

	public HomePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='ico-register']")
	private WebElement registerHeaderLink;

	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginHeaderLink;

	@FindBy(xpath = "//a[@class='ico-account']")
	private WebElement myAccountHeaderLink;

	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutHeaderLink;

	public boolean isRegisterHeaderLinkDisplayed() {
		waitForElementVisible(driver, registerHeaderLink);
		return isElementDisplayed(driver, registerHeaderLink);
	}

	public void clickRegisterHeaderLink() {
		waitForElementClickable(driver, registerHeaderLink);
		clickElement(driver, registerHeaderLink);
	}

	public boolean isLoginHeaderLinkDisplayed() {
		waitForElementVisible(driver, loginHeaderLink);
		return isElementDisplayed(driver, loginHeaderLink);
	}

	public void clickLoginHeaderLink() {
		waitForElementClickable(driver, loginHeaderLink);
		clickElement(driver, loginHeaderLink);
	}

	public boolean isMyAccountHeaderLinkDisplayed() {
		waitForElementVisible(driver, myAccountHeaderLink);
		return isElementDisplayed(driver, myAccountHeaderLink);
	}

	public void clickMyAccountHeaderLink() {
		waitForElementClickable(driver, myAccountHeaderLink);
		clickElement(driver, myAccountHeaderLink);
	}

	public boolean isLogoutHeaderLinkDisplayed() {
		waitForElementVisible(driver, logoutHeaderLink);
		return isElementDisplayed(driver, logoutHeaderLink);
	}

}
