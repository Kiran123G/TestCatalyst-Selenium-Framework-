package com.framework.utilities;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PathChecker {

    // Method to check if a file exists in the project directory
    public static boolean isFileExists(String filePath) {
        try {
            File file = new File(filePath);
            return file.exists() && !file.isDirectory();
        } catch (Exception e) {
            LoggerUtility.error("Error checking file path: " + filePath, e);
            return false;
        }
    }

    // Method to check if a resource exists in the classpath
    public static boolean isResourceExists(String resourcePath) {
        try {
            URL resourceUrl = PathChecker.class.getClassLoader().getResource(resourcePath);
            return resourceUrl != null;
        } catch (Exception e) {
            LoggerUtility.error("Error checking resource path: " + resourcePath, e);
            return false;
        }
    }

    // Method to check if a file exists at a specific location (physical file system)
    public static boolean isPhysicalFileExists(String physicalFilePath) {
        try {
            return Files.exists(Paths.get(physicalFilePath));
        } catch (Exception e) {
            LoggerUtility.error("Error checking physical file path: " + physicalFilePath, e);
            return false;
        }
    }

    // Method to check if a file exists by both physical path and classpath
    public static boolean isFileOrResourceExists(String path) {
        if (isFileExists(path)) {
            return true;
        } else if (isResourceExists(path)) {
            return true;
        }
        return false;
    }
}
