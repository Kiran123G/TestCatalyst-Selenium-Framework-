package com.framework.tests.functional;

import com.framework.driver.BaseTest;
import com.framework.pageobjects.LoginPage;
import com.framework.reporting.ExtentReportManager;
import com.framework.utilities.ScreenshotUtility;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {
        // Start the test in Extent Report
        ExtentReportManager.startTest(this.getClass().getSimpleName(), "Test Execution Started");

        ScreenshotUtility screenshotUtility = new ScreenshotUtility(driver);

        try {
            // Test actions
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterUsername("testuser");
            ExtentReportManager.getTest().info("Entered username.");

            loginPage.enterPassword("password");
            ExtentReportManager.getTest().info("Entered password.");

            loginPage.clickLoginButton();
            ExtentReportManager.getTest().info("Clicked login button.");

            // If everything goes well, mark the test as passed
            ExtentReportManager.getTest().pass("Test case passed: testValidLogin");

        } catch (Exception e) {
            // Capture screenshot on failure
            String screenshotPath = screenshotUtility.captureScreenshot("testValidLogin_Failure");
            ExtentReportManager.getTest().fail("Test failed due to: " + e.getMessage());
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);

            // Log failure in the report
            ExtentReportManager.getTest().fail("Failure: " + e.toString());
        } finally {
            // End the test and flush the report
            ExtentReportManager.endTest();
        }
    }
}
