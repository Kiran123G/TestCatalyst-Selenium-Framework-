package com.framework.tests.functional;

import com.framework.configurations.ConfigurationManager;
import com.framework.utilities.LoggerUtility;
import org.testng.Assert;
import org.testng.annotations.Test;



public class ConfigurationFileTest {

    public static void main(String[] args) {
        System.out.println("Starting configuration file test...");

   
        String browser = ConfigurationManager.getConfig().browser();
        String baseUrl = ConfigurationManager.getConfig().baseUrl();
        int implicitWait = ConfigurationManager.getConfig().implicitWait();
        boolean headless = ConfigurationManager.getConfig().headlessMode();

      
        System.out.println("Browser: " + browser);
        System.out.println("Base URL: " + baseUrl);
        System.out.println("Implicit Wait: " + implicitWait);
        System.out.println("Headless Mode: " + headless);

    
        try {
            if (browser == null || browser.isBlank()) {
                throw new AssertionError("Browser property is missing or empty.");
            }
            if (baseUrl == null || baseUrl.isBlank()) {
                throw new AssertionError("Base URL property is missing or empty.");
            }
            if (implicitWait <= 0) {
                throw new AssertionError("Implicit wait should be greater than zero.");
            }

            System.out.println("Configuration file loaded successfully!");
        } catch (AssertionError e) {
            System.err.println("Configuration validation failed: " + e.getMessage());
        }
    }
}
