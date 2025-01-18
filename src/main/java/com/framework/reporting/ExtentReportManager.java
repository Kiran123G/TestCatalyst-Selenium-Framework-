package com.framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.framework.utilities.ScreenshotUtility;

import java.io.File;
import java.util.Objects;

import org.openqa.selenium.WebDriver;

public class ExtentReportManager {

    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extentReports == null) {
            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
            createReportsFolderIfNotExists();

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Automation Test Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Environment", "QA");
            extentReports.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extentReports;
    }

    private static void createReportsFolderIfNotExists() {
        String reportFolderPath = System.getProperty("user.dir") + "/reports";
        File reportFolder = new File(reportFolderPath);
        if (!reportFolder.exists()) {
            boolean isCreated = reportFolder.mkdir();
            if (isCreated) {
                System.out.println("Reports folder created at: " + reportFolderPath);
            } else {
                System.err.println("Failed to create reports folder at: " + reportFolderPath);
            }
        }
    }

    public static ExtentTest startTest(String testName, String testDescription) {
        ExtentTest test = getInstance().createTest(testName, testDescription);
        extentTestThreadLocal.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static void endTest() {
        extentReports.flush();
    }

    // Method to log failure and capture screenshots
    public static void handleFailure(Throwable throwable, WebDriver driver) {
        if (getTest() != null) {
            // Log failure in report
            getTest().fail("Test failed due to: " + throwable.getMessage());

            // Capture screenshot
            ScreenshotUtility screenshotUtility = new ScreenshotUtility(driver);
            String screenshotPath = screenshotUtility.captureScreenshot("Failure_Screenshot");

            // Add screenshot to report
            try {
                getTest().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
