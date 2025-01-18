package com.framework.utilities;

public class TestPathChecker {

    public static void main(String[] args) {
        // Checking a file in the physical file system
        boolean isFileExist = PathChecker.isFileExists("src/main/resources/configs/dev.properties");
        System.out.println("File exists: " + isFileExist);

        // Checking a resource in the classpath
        boolean isResourceExist = PathChecker.isResourceExists("configs/dev.properties");
        System.out.println("Resource exists in classpath: " + isResourceExist);

        // Checking both file system and classpath
        boolean isPathExist = PathChecker.isFileOrResourceExists("configs/dev.properties");
        System.out.println("File or resource exists: " + isPathExist);
    }
}
