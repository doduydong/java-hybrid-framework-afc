package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.facebook.LoginPageObject;

public class PageGeneratorFacebook {

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

}
