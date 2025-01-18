package com.framework.configurations;

import com.framework.utilities.PathChecker;
import org.aeonbits.owner.ConfigFactory;

public class ConfigurationManager {

    // Static variable to hold the FrameworkConfig instance
    private static FrameworkConfig config;

    // Private constructor to prevent instantiation
    private ConfigurationManager() {}

    public static FrameworkConfig getConfig() {
        if (config == null) {
            // Fetch environment from system property or default to "stage"
            String env = System.getProperty("env", "stage");
            String configFilePath = "src/main/resources/configs/" + env + ".properties";

            // Log environment and configuration file path for debugging
            System.out.println("Environment: " + env);
            System.out.println("Configuration file path: " + configFilePath);

            // Check if the configuration file exists before loading
            if (PathChecker.isFileExists(configFilePath)) {
                // Set the system property for configuration file location
                System.setProperty("config.file", configFilePath);
                
                // Create and assign the FrameworkConfig instance
                config = ConfigFactory.create(FrameworkConfig.class);
            } else {
                // If configuration file doesn't exist, throw an exception
                throw new IllegalArgumentException("Configuration file does not exist at: " + configFilePath);
            }
        }
        return config; // Return the FrameworkConfig instance
    }
}
