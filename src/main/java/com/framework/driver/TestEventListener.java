package com.framework.driver;

import com.aventstack.extentreports.Status;
import com.framework.reporting.ExtentReportManager;
import com.framework.utilities.LoggerUtility;
import com.framework.utilities.ScreenshotUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestEventListener implements ITestListener {

    private WebDriver driver;
    public TestEventListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.startTest(testName, "Test started: " + testName);
        LoggerUtility.info("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.getTest().log(Status.PASS, "Test passed: " + testName);
        LoggerUtility.info("Test passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        
       
        ScreenshotUtility screenshotUtility = new ScreenshotUtility(driver);
        String screenshotPath = screenshotUtility.captureScreenshot(testName);

      
        ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + testName);
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
        ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);  
        LoggerUtility.error("Test failed: " + testName, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReportManager.getTest().log(Status.SKIP, "Test skipped: " + testName);
        LoggerUtility.warn("Test skipped: " + testName);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.endTest();
        LoggerUtility.info("Test execution completed.");
    }
}
