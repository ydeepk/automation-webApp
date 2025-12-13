package com.webapp.framework.pages;

import com.webapp.framework.driver.DriverManager;
import com.webapp.framework.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * BasePage serves as the parent class for all page objects.
 *
 * It centralizes WebDriver access, logging, and common element
 * interaction utilities such as waits, clicks, and text entry.
 */
public class BasePage {

    /** WebDriver instance used for browser interactions. */
    protected final WebDriver DRIVER;

    /** Logger instance scoped to the concrete page class. */
    protected final Logger LOG;

    /**
     * Constructs a BasePage instance.
     *
     * Initializes the WebDriver from DriverManager and configures
     * a class-specific logger.
     *
     * @throws IllegalStateException if WebDriver is not initialized
     */
    public BasePage() {
        this.DRIVER = DriverManager.getDriver();
        if (this.DRIVER == null) {
            throw new IllegalStateException("WebDriver is not initialized");
        }
        this.LOG = LogManager.getLogger(getClass());
    }

    /**
     * Waits until the specified element becomes visible.
     *
     * @param locator the element locator
     * @return the visible WebElement
     */
    protected final WebElement waitForVisible(By locator) {
        return WaitUtils.waitForVisible(locator);
    }

    /**
     * Waits until the specified element becomes clickable.
     *
     * @param locator the element locator
     * @return the clickable WebElement
     */
    protected final WebElement waitForClickable(By locator) {
        return WaitUtils.waitForClickable(locator);
    }

    /**
     * Clicks the specified element after waiting for clickability.
     *
     * @param locator the element locator
     */
    protected void click(By locator) {
        LOG.info("Clicking element: {}", locator);
        waitForClickable(locator).click();
    }

    /**
     * Types text into the specified input element.
     *
     * Clears any existing content before entering new text.
     *
     * @param locator the input element locator
     * @param inputText the text to enter
     */
    protected void type(By locator, String inputText) {
        LOG.info("Typing into element: {} with text: {}", locator, inputText);
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(inputText);
    }

    /**
     * Returns the visible text of the specified element.
     *
     * @param locator the element locator
     * @return the element text
     */
    protected String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    /**
     * Determines whether the specified element is displayed.
     *
     * @param locator the element locator
     * @return true if the element is visible, otherwise false
     */
    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisible(locator).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            LOG.debug("Element not displayed: {}", locator, e);
            return false;
        }
    }

    /**
     * Navigates the browser to the specified URL.
     *
     * @param url the destination URL
     */
    public void goToUrl(String url) {
        LOG.info("Navigating to URL: {}", url);
        DRIVER.get(url);
    }
}