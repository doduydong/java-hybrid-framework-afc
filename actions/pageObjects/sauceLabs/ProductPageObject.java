package pageObjects.sauceLabs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sauceLabs.ProductPageUI;

public class ProductPageObject extends BasePage {
	private WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isHeaderLabelDisplayed() {
		waitForElementVisible(driver, ProductPageUI.HEADER_LABEL);
		return isElementDisplayed(driver, ProductPageUI.HEADER_LABEL);
	}

	public int verifyNumberOfDisplayedProducts() {
		waitForAllElementsVisible(driver, ProductPageUI.PRODUCTS);
		return getNumberOfElements(driver, ProductPageUI.PRODUCTS);
	}

	public void sortProducts(String option) {
		waitForElementClickable(driver, ProductPageUI.PRODUCTS_SORT_DROPDOWN);
		selectOptionInDefaultDropdown(driver, ProductPageUI.PRODUCTS_SORT_DROPDOWN, option);
	}

	public boolean isProductNamesSortedAscending() {
		waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_NAMES);
		List<WebElement> productNames = getListWebElement(driver, ProductPageUI.PRODUCT_NAMES);
		List<String> productNamesList = new ArrayList<>();
		for (WebElement name : productNames) {
			productNamesList.add(name.getText());
		}
		List<String> productNamesSorted = new ArrayList<>(productNamesList);
		Collections.sort(productNamesSorted);
		for (String name : productNamesSorted) {
			System.out.println(name);
		}
		return productNamesList.equals(productNamesSorted);
	}

	public boolean isProductNamesSortedDescending() {
		waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_NAMES);
		List<WebElement> productNames = getListWebElement(driver, ProductPageUI.PRODUCT_NAMES);
		List<String> productNamesList = new ArrayList<>();
		for (WebElement name : productNames) {
			productNamesList.add(name.getText());
		}
		List<String> productNamesSorted = new ArrayList<>(productNamesList);
		Collections.sort(productNamesSorted, Collections.reverseOrder());
		for (String name : productNamesSorted) {
			System.out.println(name);
		}
		return productNamesList.equals(productNamesSorted);
	}

	public boolean isProductPricesSortedAscending() {
		waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_PRICES);
		List<WebElement> productPrices = getListWebElement(driver, ProductPageUI.PRODUCT_PRICES);
		List<Double> productPricesList = new ArrayList<>();
		for (WebElement price : productPrices) {
			productPricesList.add(Double.parseDouble(price.getText().replace("$", "")));
		}
		List<Double> productPricesSorted = new ArrayList<>(productPricesList);
		Collections.sort(productPricesSorted);
		for (Double price : productPricesSorted) {
			System.out.println("$" + String.format("%.2f", price));
		}
		return productPricesList.equals(productPricesSorted);
	}

	public boolean isProductPricesSortedDescending() {
		waitForAllElementsVisible(driver, ProductPageUI.PRODUCT_PRICES);
		List<WebElement> productPrices = getListWebElement(driver, ProductPageUI.PRODUCT_PRICES);
		List<Double> productPricesList = new ArrayList<>();
		for (WebElement price : productPrices) {
			productPricesList.add(Double.parseDouble(price.getText().replace("$", "")));
		}
		List<Double> productPricesSorted = new ArrayList<>(productPricesList);
		Collections.sort(productPricesSorted, Collections.reverseOrder());
		for (Double price : productPricesSorted) {
			System.out.println("$" + String.format("%.2f", price));
		}
		return productPricesList.equals(productPricesSorted);
	}

}
