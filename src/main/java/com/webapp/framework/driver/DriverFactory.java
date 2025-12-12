package com.webapp.framework.driver;

import com.webapp.framework.config.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Factory class responsible for creating WebDriver instances based on
 * configuration values. This class reads the desired browser and headless
 * mode from ConfigLoader and returns the corresponding WebDriver instance.
 * The browser driver binaries are automatically managed by WebDriverManager.
 */
public final class DriverFactory {

    /**
     * Private constructor to prevent instantiation. This class is intended to be
     * used only through its static methods.
     */
    private DriverFactory() {}

    /**
     * Creates and returns a WebDriver instance according to the configured
     * browser and headless settings.
     *
     * Supported browsers include:
     * - chrome
     * - firefox
     * - edge
     * - safari
     *
     * @return a configured WebDriver instance
     * @throws RuntimeException if an unsupported browser type is configured
     */
    public static WebDriver createInstance() {
        // Retrieve browser configuration (for example: "chrome", "firefox")
        String browser = ConfigLoader.get("browser").toLowerCase();

        // Retrieve headless mode flag.
        boolean headless = ConfigLoader.getBoolean("headless");

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                // Apply headless mode if configured.
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }

                // Start Chrome maximized.
                chromeOptions.addArguments("--start-maximized");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "safari":
                // SafariDriver is built into macOS. WebDriverManager verification is harmless.
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();

            default:
                // Invalid browser type specified in configuration.
                throw new RuntimeException("Browser not supported: " + browser);
        }
    }
}