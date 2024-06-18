package reportConfig;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.BaseTest;
import commons.GlobalConstants;

public class ExtentReportsListener implements ITestListener {
	private static final ExtentReports extentReports = createExtentReports();
	private static final Map<Integer, ExtentTest> extentTestMap = new ConcurrentHashMap<>();

	private static ExtentReports createExtentReports() {
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(GlobalConstants.EXTENT_REPORTS_OUTPUT);

		sparkReporter.config().setReportName("ExtentReports - nopCommerce");
		sparkReporter.config().setDocumentTitle("ExtentReports - nopCommerce");
		sparkReporter.config().setTimelineEnabled(true);
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Company", "AFC");
		extentReports.setSystemInfo("Project", "nopCommerce");
		extentReports.setSystemInfo("SDET", "Dong");
		extentReports.setSystemInfo("JDK Version", GlobalConstants.JDK_VERSION);

		return extentReports;
	}

	public static synchronized ExtentTest startTest(String testName, String stepDesc) {
		ExtentTest test = extentReports.createTest(testName, stepDesc);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) Thread.currentThread().getId());
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		getTest().log(Status.PASS, result.getName() + " [PASSED]");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Object testInstance = result.getInstance();
		WebDriver driver = ((BaseTest) testInstance).getDriver();
		String screenshotBase64 = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL, result.getThrowable().getMessage(), getTest().addScreenCaptureFromBase64String(screenshotBase64).getModel().getMedia().get(0));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		getTest().log(Status.SKIP, result.getThrowable().getMessage());
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
		extentReports.flush();
		File extentReport = new File(GlobalConstants.EXTENT_REPORTS_OUTPUT);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
