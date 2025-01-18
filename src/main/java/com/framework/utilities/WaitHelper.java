package com.framework.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriver driver;

    public WaitHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
        LoggerUtility.info("Waiting for element to be visible: " + element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(element));
        LoggerUtility.info("Element is visible: " + element);
        return visibleElement;
    }

    public boolean waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
        LoggerUtility.info("Waiting for element to be clickable: " + element);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        boolean isClickable = wait.until(ExpectedConditions.elementToBeClickable(element)) != null;
        LoggerUtility.info("Element is clickable: " + element);
        return isClickable;
    }
}
