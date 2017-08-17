package web.utils;

import api.utils.ReportWriter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        Object iTestResult = tr.getInstance();
        WebDriverWrapper webDriver = ((TestBase) iTestResult).getDriver();

        Reporter.setCurrentTestResult(tr);
        takeScreenShot(tr, webDriver);
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

    private File takeScreenShot(ITestResult testResult, WebDriverWrapper webDriver) {
        File screenshotFile = null;
        try {
            DateFormat dateFormatForFile = new SimpleDateFormat("HH-mm-ss-SSS");
            DateFormat dateFormatForDir = new SimpleDateFormat("yyyy-MM-dd");

            String filename = dateFormatForFile.format(new Date()) + "_" + testResult.getMethod().getMethodName() + ".png";
            String screenshotsDir = System.getProperty("user.dir").replace("\\", "/") + "/target/surefire-reports/html/Screenshots/";
            String destDir = screenshotsDir + dateFormatForDir.format(new Date());
            String destDirForReport = "Screenshots/" + dateFormatForDir.format(new Date());

            File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            screenshotFile = new File(destDir + File.separator + filename);
            FileUtils.copyFile(screenshot, screenshotFile);

            ReportWriter.logScreenShot(destDirForReport + "/" + filename);

        } catch (Exception e) {
            ReportWriter.logException("Exception during takeScreenShot. " + e.toString());
        }
        return screenshotFile;
    }


}
