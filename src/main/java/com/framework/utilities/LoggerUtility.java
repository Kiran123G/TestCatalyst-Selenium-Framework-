package com.framework.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

    private static final Logger logger = LogManager.getLogger();

    private LoggerUtility() {
       	
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
