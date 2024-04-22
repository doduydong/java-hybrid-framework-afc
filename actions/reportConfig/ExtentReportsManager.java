package reportConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import commons.GlobalConstants;

public class ExtentReportsManager {
	public static final ExtentReports extentReports = createExtentReports();
	private static final Map<Integer, ExtentTest> extentTestMap = new ConcurrentHashMap<>();

	private static ExtentReports createExtentReports() {
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalConstants.EXTENT_REPORTS_OUTPUT);
		reporter.config().setReportName("ExtentReports - nopCommerce");
		reporter.config().setDocumentTitle("ExtentReports - nopCommerce");
		reporter.config().setTimelineEnabled(true);
		reporter.config().setEncoding("utf-8");
		reporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(reporter);
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

}
