package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	// Browser commands

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getWindowID(WebDriver driver) {
		return driver.getWindowHandle();
	}

	protected Set<String> getAllWindowIDs(WebDriver driver) {
		return driver.getWindowHandles();
	}

	protected void moveBack(WebDriver driver) {
		driver.navigate().back();
	}

	protected void moveForward(WebDriver driver) {
		driver.navigate().forward();
	}

	protected void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected void navigateTo(WebDriver driver, String pageUrl) {
		driver.navigate().to(pageUrl);
	}

	private Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected void sendKeysToAlert(WebDriver driver, String keysToSend) {
		waitForAlertPresence(driver).sendKeys(keysToSend);
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void switchToWindowDifferentFromCurrentByID(WebDriver driver, String currentID) {
		Set<String> allWindowIDs = getAllWindowIDs(driver);
		for (String id : allWindowIDs) {
			if (!id.equals(currentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switchToWindowByExpectedTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowIDs = getAllWindowIDs(driver);
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			if (getPageTitle(driver).equals(expectedTitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowsExceptWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = getAllWindowIDs(driver);
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(windowID);
	}

	protected Set<Cookie> getCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	protected void addCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	// WebElement commands

	private By getByXPath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private String getDynamicXPath(String xpathLocator, String... dynamicValues) {
		return String.format(xpathLocator, (Object[]) dynamicValues);
	}

	protected WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXPath(xpathLocator));
	}

	protected WebElement getWebElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return driver.findElement(getByXPath(getDynamicXPath(xpathLocator, dynamicValues)));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXPath(xpathLocator));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return driver.findElements(getByXPath(getDynamicXPath(xpathLocator, dynamicValues)));
	}

	protected void clickElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}

	protected void clickElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		getWebElement(driver, xpathLocator, dynamicValues).click();
	}

	protected void sendKeysToElement(WebDriver driver, String xpathLocator, String keysToSend) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(keysToSend);
	}

	protected void sendKeysToElement(WebDriver driver, String xpathLocator, String keysToSend, String... dynamicValues) {
		WebElement element = getWebElement(driver, xpathLocator, dynamicValues);
		element.clear();
		element.sendKeys(keysToSend);
	}

	protected String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}

	protected String getElementText(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, xpathLocator, dynamicValues).getText();
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String... dynamicValues) {
		return getWebElement(driver, xpathLocator, dynamicValues).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName, String... dynamicValues) {
		return getWebElement(driver, xpathLocator, dynamicValues).getCssValue(propertyName);
	}

	protected String getElementHexColor(WebDriver driver, String xpathLocator, String propertyName) {
		return Color.fromString(getElementCssValue(driver, xpathLocator, propertyName)).asHex().toUpperCase();
	}

	protected String getElementHexColor(WebDriver driver, String xpathLocator, String propertyName, String... dynamicValues) {
		return Color.fromString(getElementCssValue(driver, xpathLocator, propertyName, dynamicValues)).asHex().toUpperCase();
	}

	protected int getNumberOfElements(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	protected int getNumberOfElements(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getListWebElement(driver, xpathLocator, dynamicValues).size();
	}

	protected boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}

	protected boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, xpathLocator, dynamicValues).isDisplayed();
	}

	protected boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		setImplicitWait(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		setImplicitWait(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isElementUndisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		setImplicitWait(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, xpathLocator, dynamicValues);
		setImplicitWait(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, xpathLocator, dynamicValues).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, xpathLocator, dynamicValues).isSelected();
	}

	protected void selectOptionInDefaultDropdown(WebDriver driver, String xpathLocator, String optionText) {
		new Select(getWebElement(driver, xpathLocator)).selectByVisibleText(optionText);
	}

	protected void selectOptionInDefaultDropdown(WebDriver driver, String xpathLocator, String optionText, String... dynamicValues) {
		new Select(getWebElement(driver, xpathLocator, dynamicValues)).selectByVisibleText(optionText);
	}

	protected String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String xpathLocator) {
		return new Select(getWebElement(driver, xpathLocator)).getFirstSelectedOption().getText();
	}

	protected String getSelectedOptionTextInDefaultDropdown(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return new Select(getWebElement(driver, xpathLocator, dynamicValues)).getFirstSelectedOption().getText();
	}

	protected boolean isDefaultDropdownMultiple(WebDriver driver, String xpathLocator) {
		return new Select(getWebElement(driver, xpathLocator)).isMultiple();
	}

	protected boolean isDefaultDropdownMultiple(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return new Select(getWebElement(driver, xpathLocator, dynamicValues)).isMultiple();
	}

	protected void checkDefaultCheckboxOrRadioButton(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkDefaultCheckboxOrRadioButton(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, xpathLocator, dynamicValues);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckDefaultCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckDefaultCheckbox(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, xpathLocator, dynamicValues);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void switchToFrame(WebDriver driver, String xpathLocator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXPath(xpathLocator)));
	}

	protected void switchToFrame(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXPath(getDynamicXPath(xpathLocator, dynamicValues))));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// Actions commands

	protected void moveMouseToElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	protected void moveMouseToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new Actions(driver).moveToElement(getWebElement(driver, xpathLocator, dynamicValues)).perform();
	}

	protected void clickAndHoldOnElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).clickAndHold(getWebElement(driver, xpathLocator)).perform();
	}

	protected void clickAndHoldOnElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new Actions(driver).clickAndHold(getWebElement(driver, xpathLocator, dynamicValues)).perform();
	}

	protected void releaseMouseFromElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).release(getWebElement(driver, xpathLocator)).perform();
	}

	protected void releaseMouseFromElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new Actions(driver).release(getWebElement(driver, xpathLocator, dynamicValues)).perform();
	}

	protected void rightClickOnElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).contextClick(getWebElement(driver, xpathLocator)).perform();
	}

	protected void rightClickOnElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new Actions(driver).contextClick(getWebElement(driver, xpathLocator, dynamicValues)).perform();
	}

	protected void doubleClickOnElement(WebDriver driver, String xpathLocator) {
		new Actions(driver).doubleClick(getWebElement(driver, xpathLocator)).perform();
	}

	protected void doubleClickOnElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new Actions(driver).doubleClick(getWebElement(driver, xpathLocator, dynamicValues)).perform();
	}

	protected void pressKeyOnElement(WebDriver driver, String xpathLocator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, xpathLocator), key).perform();
	}

	protected void pressKeyOnElement(WebDriver driver, String xpathLocator, Keys key, String... dynamicValues) {
		new Actions(driver).sendKeys(getWebElement(driver, xpathLocator, dynamicValues), key).perform();
	}

	// JavascriptExecutor commands

	protected Object executeJS(WebDriver driver, String javascript) {
		return ((JavascriptExecutor) driver).executeScript(javascript);
	}

	public void scrollToTopByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-document.body.scrollHeight);");
	}

	protected void scrollToBottomByJS(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight);");
	}

	protected void scrollElementIntoViewTopByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	protected void scrollElementIntoViewTopByJS(WebDriver driver, String xpathLocator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator, dynamicValues));
	}

	protected void scrollElementIntoViewBottomByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, xpathLocator));
	}

	protected void scrollElementIntoViewBottomByJS(WebDriver driver, String xpathLocator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, xpathLocator, dynamicValues));
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, xpathLocator, dynamicValues));
	}

	protected String getElementValidationMessageByJS(WebDriver driver, String xpathLocator) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	protected String getElementValidationMessageByJS(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator, dynamicValues));
	}

	protected void setElementAttributeByJS(WebDriver driver, String xpathLocator, String attributeName, String attributeValue) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, xpathLocator));
	}

	protected void setElementAttributeByJS(WebDriver driver, String xpathLocator, String attributeName, String attributeValue, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, xpathLocator, dynamicValues));
	}

	protected void removeElementAttributeByJS(WebDriver driver, String xpathLocator, String attributeName) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeName + "');", getWebElement(driver, xpathLocator));
	}

	protected void removeElementAttributeByJS(WebDriver driver, String xpathLocator, String attributeName, String... dynamicValues) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeName + "');", getWebElement(driver, xpathLocator, dynamicValues));
	}

	protected void highlightElementByJS(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-style: dashed;');", element);
		sleepInSecond(oneSec);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	}

	protected void highlightElementByJS(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, xpathLocator, dynamicValues);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: 2px solid red; border-style: dashed;');", element);
		sleepInSecond(oneSec);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	}

	protected boolean isImageLoadedByJS(WebDriver driver, String xpathLocator) {
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;", getWebElement(driver, xpathLocator));
	}

	protected boolean isImageLoadedByJS(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;", getWebElement(driver, xpathLocator, dynamicValues));
	}

	protected void waitForPageReady(WebDriver driver) {
		new WebDriverWait(driver, longTimeout).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver).executeScript("return (jQuery != null) && (jQuery.active === 0) && (document.readyState === 'complete');");
			}
		});
	}

	// Wait commands

	protected void waitForElementVisible(WebDriver driver, String xpathLocator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXPath(xpathLocator)));
	}

	protected void waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXPath(getDynamicXPath(xpathLocator, dynamicValues))));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXPath(xpathLocator)));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXPath(getDynamicXPath(xpathLocator, dynamicValues))));
	}

	protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		setImplicitWait(driver, shortTimeout);
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXPath(xpathLocator)));
		setImplicitWait(driver, longTimeout);
	}

	protected void waitForElementInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		setImplicitWait(driver, shortTimeout);
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXPath(getDynamicXPath(xpathLocator, dynamicValues))));
		setImplicitWait(driver, longTimeout);
	}

	protected void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
		setImplicitWait(driver, shortTimeout);
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
		setImplicitWait(driver, longTimeout);
	}

	protected void waitForAllElementsInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		setImplicitWait(driver, shortTimeout);
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator, dynamicValues)));
		setImplicitWait(driver, longTimeout);
	}

	protected void waitForElementClickable(WebDriver driver, String xpathLocator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXPath(xpathLocator)));
	}

	protected void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXPath(getDynamicXPath(xpathLocator, dynamicValues))));
	}

	// File upload method
	protected void uploadFile(WebDriver driver, String... fileNames) {
		String fileUploadFolderPath = GlobalConstants.FILE_UPLOAD_FOLDER_PATH;
		for (String fileName : fileNames) {
			getWebElement(driver, "//input[@type='file']").sendKeys(fileUploadFolderPath + fileName);
		}
	}

	protected void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long oneSec = GlobalConstants.ONE_SEC;

}
