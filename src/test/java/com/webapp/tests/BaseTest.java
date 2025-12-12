package com.webapp.tests;

import com.webapp.framework.config.ConfigLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeSuite() {
        log.info("Starting test run. env= "+System.getProperty("env", "config"));
        log.info("baseURL= "+ ConfigLoader.get("baseURL"));
        log.info("browser= "+ConfigLoader.get("browser"));
    }
}
