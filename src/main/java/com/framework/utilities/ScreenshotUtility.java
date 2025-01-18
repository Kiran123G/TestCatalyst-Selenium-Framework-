package com.framework.utilities;

import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.framework.reporting.ExtentReportManager;

public class ScreenshotUtility {

	private static final Logger logger = LogManager.getLogger();
	private final WebDriver driver;

	private static final String SCREENSHOT_FOLDER = System.getProperty("user.dir") + "/reports/screenshots/";

	public ScreenshotUtility(WebDriver driver) {
		this.driver = driver;
	}

	public String captureScreenshot(String testName) {
		String screenshotPath = null;
		try {

			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String screenshotName = testName + "_" + timestamp + ".png";
			screenshotPath = SCREENSHOT_FOLDER + screenshotName;
			File folder = new File(SCREENSHOT_FOLDER);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
			logger.info("Screenshot captured: " + screenshotPath);
			attachScreenshotToReport(screenshotPath);
		} catch (IOException e) {
			logger.error("Failed to capture screenshot", e);
		}
		return screenshotPath;
	}

	private void attachScreenshotToReport(String screenshotPath) {
		try {

			if (ExtentReportManager.getTest() != null) {

				ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
				logger.info("Screenshot attached to report: " + screenshotPath);
			} else {
				logger.warn("ExtentTest is not initialized, skipping screenshot attachment to report.");
			}
		} catch (Exception e) {
			logger.error("Error attaching screenshot to report", e);
		}
	}
}
