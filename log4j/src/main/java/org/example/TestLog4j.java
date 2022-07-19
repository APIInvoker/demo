package org.example;

import org.apache.log4j.Logger;

public class TestLog4j {

    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(TestLog4j.class);
        final Logger saveUserLog = Logger.getLogger("saveUserLog");

        if (logger.isDebugEnabled()) {
            logger.debug("debug");
        }

        logger.info("info");
        logger.error("error");

        logger.warn("warn");
        saveUserLog.info("张三,男,26岁,北京大学,2018-05-19,学霸");

    }
}