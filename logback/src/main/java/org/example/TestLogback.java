package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogback {

    public static void main(String[] args) {
        final Logger LOGGER = LoggerFactory.getLogger(TestLogback.class);
        LOGGER.debug("print debug log.");
        LOGGER.info("print info log.");
        LOGGER.error("print error log.");
    }
}