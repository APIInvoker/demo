package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog4j2 {

    public static void main(String[] args) {
        final Logger LOGGER = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        LOGGER.debug("TestLog4j2 debug log.");
        LOGGER.info("TestLog4j2 info log.");
        LOGGER.error("TestLog4j2 error log.");
    }

}