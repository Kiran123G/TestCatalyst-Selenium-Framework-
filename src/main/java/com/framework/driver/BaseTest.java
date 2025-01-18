package com.framework.driver;

import com.framework.configurations.ConfigurationManager;
import com.framework.reporting.ExtentReportManager;
import com.framework.utilities.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;

public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		LoggerUtility.info("Setting up test execution.");
		driver = WebDriverManager.getDriver();
		driver.get(ConfigurationManager.getConfig().baseUrl());
		LoggerUtility.info("Navigated to URL: " + ConfigurationManager.getConfig().baseUrl());
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			// If test fails, handle failure globally
			ExtentReportManager.handleFailure(result.getThrowable(), driver);
		}

		LoggerUtility.info("Tearing down test execution.");
		WebDriverManager.quitDriver();
	}
}
