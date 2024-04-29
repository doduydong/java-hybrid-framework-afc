package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.sauceLabs.LoginPageObject;
import pageObjects.sauceLabs.ProductPageObject;

public class PageGeneratorSauceLabs {

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static ProductPageObject getProductPage(WebDriver driver) {
		return new ProductPageObject(driver);
	}

}
