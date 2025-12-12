package com.webapp.framework.driver;

import org.openqa.selenium.WebDriver;

/**
 * Manages WebDriver instances using ThreadLocal to support parallel execution.
 */
public final class DriverManager {

    // Thread-local storage of WebDriver instances for parallel test execution.
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    // Private constructor to prevent instantiation.
    private DriverManager() {}

    /**
     * Returns the WebDriver associated with the current thread.
     *
     * @return the current thread's WebDriver instance, or null if none has been set
     */
    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    /**
     * Associates the given WebDriver instance with the current thread.
     *
     * @param driver the WebDriver to associate with this thread
     */
    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    /**
     * Removes the WebDriver associated with the current thread to prevent memory leaks.
     */
    public static void unload() {
        DRIVER.remove();
    }
}