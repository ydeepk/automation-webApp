package com.webapp.framework.utils;

import com.webapp.framework.config.ConfigLoader;
import com.webapp.framework.driver.DriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class providing explicit wait operations such as waiting for element
 * visibility or clickability. This class centralizes wait configurations to
 * maintain consistency across the framework.
 */
public final class WaitUtils {

    /**
     * Private constructor to prevent instantiation. This class should only be
     * accessed through its static methods.
     */
    private WaitUtils() {}

    /**
     * Creates and returns a WebDriverWait instance using the configured explicit
     * wait timeout value.
     *
     * @return a WebDriverWait instance
     */
    private static WebDriverWait getWait() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(ConfigLoader.getInt("explicitWait"))
        );
    }

    /**
     * Waits until an element located by the given locator becomes visible.
     *
     * @param locator the element locator
     * @return the visible WebElement
     */
    public static WebElement waitForVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until an element located by the given locator becomes clickable.
     *
     * @param locator the element locator
     * @return the clickable WebElement
     */
    public static WebElement waitForClick(By locator) {
        return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
}