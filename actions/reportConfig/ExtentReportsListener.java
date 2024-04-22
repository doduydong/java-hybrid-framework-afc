package reportConfig;

import static reportConfig.ExtentReportsManager.getTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import commons.BaseTest;

public class ExtentReportsListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		getTest().log(Status.PASS, "PASSED");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Object testInstance = result.getInstance();
		WebDriver driver = ((BaseTest) testInstance).getDriver();
		String screenshotBase64 = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL, "FAILED", getTest().addScreenCaptureFromBase64String(screenshotBase64).getModel().getMedia().get(0));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		getTest().log(Status.SKIP, "SKIPPED");
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
		ExtentReportsManager.extentReports.flush();
	}

}
