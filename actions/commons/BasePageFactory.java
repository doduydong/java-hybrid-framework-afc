package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {

	protected void clickElement(WebDriver driver, WebElement element) {
		element.click();
	}

	protected void sendKeysToElement(WebDriver driver, WebElement element, String keysToSend) {
		element.clear();
		element.sendKeys(keysToSend);
	}

	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}

	protected String getElementAttribute(WebDriver driver, WebElement element, String attributeName) {
		return element.getAttribute(attributeName);
	}

	protected boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}

	protected boolean isElementSelected(WebDriver driver, WebElement element) {
		return element.isSelected();
	}

	protected void selectOptionInDefaultDropdown(WebDriver driver, WebElement element, String optionText) {
		new Select(element).selectByVisibleText(optionText);
	}

	protected String getSelectedOptionTextInDefaultDropdown(WebDriver driver, WebElement element) {
		return new Select(element).getFirstSelectedOption().getText();
	}

	protected void checkDefaultCheckboxOrRadioButton(WebDriver driver, WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(element));
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;

}
