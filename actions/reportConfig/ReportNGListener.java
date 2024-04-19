package reportConfig;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import commons.BaseTest;

public class ReportNGListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Object testInstance = result.getInstance();
			WebDriver driver = ((BaseTest) testInstance).getDriver();
			String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			Reporter.log("<br><a href=\"data:image/png;base64," + screenshotBase64 + "\">" + "<img src=\"data:image/png;base64," + screenshotBase64 + "\" " + "height='100' width='150'/> " + "</a></br>");
		} catch (NoSuchSessionException e) {
			e.printStackTrace();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
