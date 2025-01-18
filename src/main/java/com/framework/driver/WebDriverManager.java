package com.framework.driver;

import com.framework.configurations.ConfigurationManager;
import com.framework.utilities.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverManager() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // Fetch browser and headless configuration directly from the config
            String browser = ConfigurationManager.getConfig().browser().toLowerCase();
            boolean headless = ConfigurationManager.getConfig().headlessMode();
            
            LoggerUtility.info("Initializing browser: " + browser);

            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    if (headless) chromeOptions.addArguments("--headless");
                    driver.set(new ChromeDriver(chromeOptions));
                    LoggerUtility.info("Chrome browser initialized successfully.");
                    break;
                case "firefox":
                    driver.set(new FirefoxDriver());
                    LoggerUtility.info("Firefox browser initialized successfully.");
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    if (headless) edgeOptions.addArguments("--headless");
                    driver.set(new EdgeDriver(edgeOptions));
                    LoggerUtility.info("Edge browser initialized successfully.");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigurationManager.getConfig().implicitWait()));
            driver.get().manage().window().maximize();
            LoggerUtility.info("Browser session started successfully.");
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            LoggerUtility.info("Closing the browser session.");
            driver.get().quit();
            driver.remove();
            LoggerUtility.info("Browser session closed.");
        }
    }
}
