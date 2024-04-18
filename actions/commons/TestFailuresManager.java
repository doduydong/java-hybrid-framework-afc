package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestResult;

public class TestFailuresManager extends ConcurrentHashMap<ITestResult, List<Throwable>> {
	private static final long serialVersionUID = 1L;
	private static TestFailuresManager testFailures;

	private TestFailuresManager() {
		super();
	}

	public static TestFailuresManager getTestFailures() {
		if (testFailures == null) {
			testFailures = new TestFailuresManager();
		}
		return testFailures;
	}

	public List<Throwable> getFailedResults(ITestResult testResult) {
		List<Throwable> failedResults = get(testResult);
		return failedResults == null ? new ArrayList<Throwable>() : failedResults;
	}

	public void addFailedResults(ITestResult testResult, Throwable throwable) {
		List<Throwable> failedResults = getFailedResults(testResult);
		failedResults.add(throwable);
		put(testResult, failedResults);
	}

}
