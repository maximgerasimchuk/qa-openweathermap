package api.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * Created by herasymchuk on 8/17/2017.
 */
public class CustomTestListener extends TestBase implements ITestListener {
    @Override
    public void onTestStart(ITestResult tr) {
        tr.setStatus(ITestResult.STARTED); //redundant??
        Reporter.setCurrentTestResult(tr);
        StringBuffer testNameWithParameters = new StringBuffer(tr.getMethod().getMethodName());
        Object[] obj = tr.getParameters();
        if (obj.length > 0) {
            testNameWithParameters.append("[");
            for (int i = 0; i < obj.length; i++) {
                testNameWithParameters.append(obj[i].toString() + ", ");
            }
            testNameWithParameters.append("]");
        }
        ReportWriter.logTestCaseName(testNameWithParameters.toString().replace(", ]", "]"));
        ReportWriter.logTestDescription(tr.getMethod().getDescription());
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        ReportWriter.logInfo("TEST PASSED");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        Reporter.setCurrentTestResult(tr);
        Reporter.setCurrentTestResult(null);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
