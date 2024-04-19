package commons;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

public class TestFailuresListener implements IInvokedMethodListener {
	private static final Logger log = LogManager.getLogger(TestFailuresListener.class);

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		log.debug("Before invocation of " + method.getTestMethod().getMethodName());
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		log.debug("After invocation of " + method.getTestMethod().getMethodName());
		Reporter.setCurrentTestResult(testResult);
		if (method.isTestMethod()) {
			TestFailuresManager testFailures = TestFailuresManager.getTestFailures();
			if (testResult.getThrowable() != null) {
				testFailures.addFailedResults(testResult, testResult.getThrowable());
			}
			List<Throwable> failedResults = testFailures.getFailedResults(testResult);
			int size = failedResults.size() - 1;
			if (size > 0) {
				testResult.setStatus(ITestResult.FAILURE);
				if (size == 1) {
					testResult.setThrowable(failedResults.get(0));
				} else {
					StringBuffer message = new StringBuffer("Multiple failures (").append(size).append("):\n");
					for (int failedResult = 0; failedResult < size - 1; failedResult++) {
						message.append("Failure ").append(failedResult + 1).append(" of ").append(size).append("\n");
						message.append(Utils.longStackTrace(failedResults.get(failedResult), false)).append("\n");
					}
					Throwable last = failedResults.get(size - 1);
					message.append("Failure ").append(size).append(" of ").append(size).append("\n");
					message.append(last.toString());
					Throwable merged = new Throwable(message.toString());
					merged.setStackTrace(last.getStackTrace());
					testResult.setThrowable(merged);
				}
			}
		}
	}

}
