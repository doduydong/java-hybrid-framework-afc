package afc.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FW_01_YAGNI_KISS_DRY {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, password, company, date, month, year;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

		firstName = "Dong";
		lastName = "Do";
		emailAddress = "dongafc" + getRandomNumber() + "@gmail.com";
		password = "Selenium3@";
		company = "AFC";
		date = "13";
		month = "October";
		year = "1997";
	}

	@Test
	public void User_01_Register() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		WebElement maleRadioButton = driver.findElement(By.xpath("//input[@id='gender-male']"));
		if (!maleRadioButton.isSelected()) {
			maleRadioButton.click();
		}

		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);

		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText(date);

		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText(month);

		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText(year);

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(company);

		WebElement newsletterCheckbox = driver.findElement(By.xpath("//input[@id='Newsletter']"));
		if (!newsletterCheckbox.isSelected()) {
			newsletterCheckbox.click();
		}

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

		driver.findElement(By.xpath("//button[@id='register-button']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");

		driver.findElement(By.xpath("//a[contains(@class,'register-continue-button')]")).click();
	}

	@Test
	public void User_02_Login() {
		driver.findElement(By.xpath("//a[@class='ico-login']")).click();

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

		driver.findElement(By.xpath("//button[contains(@class,'login-button')]")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());
	}

	@Test
	public void User_03_Customer_Info() {
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-logout']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='gender-male']")).isSelected());

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);

		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), date);

		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);

		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Company']")).getAttribute("value"), company);

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='Newsletter']")).isSelected());
	}

	@Test
	public void User_04_Logout() {
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-register']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-login']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {
		return new Random().nextInt(99999);
	}

}
