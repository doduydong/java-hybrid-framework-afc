package pageUIs.sauceLabs;

public class ProductPageUI {

	public static final String HEADER_LABEL = "//div[@class='header_label']/div[text()='Swag Labs']";
	public static final String PRODUCTS = "//div[@class='inventory_list']//div[@class='inventory_item']";
	public static final String PRODUCT_NAMES = PRODUCTS + "//div[@class='inventory_item_name ']";
	public static final String PRODUCT_PRICES = PRODUCTS + "//div[@class='inventory_item_price']";
	public static final String PRODUCTS_SORT_DROPDOWN = "//select[@class='product_sort_container']";

}
