package com.webapp.tests;

import com.webapp.framework.config.ConfigLoader;
import com.webapp.framework.driver.DriverFactory;
import com.webapp.framework.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Base test class used for setting up and cleaning up WebDriver instances
 * before and after each test. This class also handles logging of suite-level
 * information such as environment, base URL, and browser type.
 */
public class BaseTest {

    protected static final Logger LOG = LogManager.getLogger(BaseTest.class);

    /**
     * Executes once before the entire test suite begins. Logs environment and
     * framework configuration details to help with debugging and reporting.
     */
    @BeforeSuite
    public void beforeSuite() {
        LOG.info("== STARTING AUTOMATION SUITE ==");
        LOG.info("Environment= " + System.getProperty("env", "config"));
        LOG.info("baseURL= " + ConfigLoader.get("baseURL"));
        LOG.info("browser= " + ConfigLoader.get("browser"));
    }

    /**
     * Executes before each test method. This method initializes a WebDriver
     * instance using DriverFactory, stores it in DriverManager, and navigates
     * to the configured base URL.
     */
    @BeforeMethod
    public void setUp() {
        LOG.info("Initializing WebDriver...");
        WebDriver driver = DriverFactory.createInstance();
        DriverManager.setDriver(driver);
        DriverManager.getDriver().get(ConfigLoader.get("baseURL"));
    }

    /**
     * Executes after each test method. This method quits the active WebDriver
     * instance and removes it from thread-local storage to prevent memory leaks.
     */
    @AfterMethod
    public void tearDown() {
        LOG.info("Quitting WebDriver...");
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}