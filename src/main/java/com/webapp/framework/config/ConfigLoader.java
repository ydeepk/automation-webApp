package com.webapp.framework.config;

import com.webapp.framework.environment.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads configuration properties from the classpath with support for base
 * configuration and environment-specific overrides.
 *
 * The loader applies the following order of precedence:
 * 1. System property "env" (-Denv=ENV)
 * 2. Property "env" inside config.properties
 * 3. Default value "DEV"
 *
 * This class loads two files:
 * - config.properties (base configuration)
 * - <env>.properties (environment overrides)
 *
 * The configuration is loaded once at startup and shared for the lifetime
 * of the application.
 */
public final class ConfigLoader {

    /** Shared properties object storing all loaded configuration values. */
    private static final Properties properties = new Properties();

    /** The active environment resolved during startup. */
    private static Environment environment;

    /** Private constructor to prevent class instantiation. */
    private ConfigLoader() {}

    // Static block initializes configuration when the class is first loaded.
    static {
        loadBaseConfig();
        loadEnvironmentConfig();
    }

    /**
     * Loads the base configuration file and determines the active environment.
     * If no system property is available, the environment is taken from the
     * base config or defaults to DEV.
     */
    private static void loadBaseConfig() {
        loadFromClasspath("config.properties");

        String env = System.getProperty(
                "env",
                properties.getProperty("env", "DEV")
        ).toUpperCase();

        environment = Environment.valueOf(env);
    }

    /**
     * Loads the configuration file for the active environment.
     * Example: qa.properties, dev.properties, prod.properties.
     */
    private static void loadEnvironmentConfig() {
        loadFromClasspath(environment.name().toLowerCase() + ".properties");
    }

    /**
     * Loads a properties file from the application's classpath and merges
     * its content into the shared properties object.
     *
     * @param filename name of the properties file to load
     * @throws RuntimeException if the file cannot be found or loaded
     */
    private static void loadFromClasspath(String filename) {
        try (InputStream input = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(filename)) {

            if (input == null) {
                throw new RuntimeException(
                        "Configuration file not found on classpath: " + filename);
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration: " + filename, e);
        }
    }

    /**
     * Returns the value for the specified configuration key.
     *
     * @param key the configuration key
     * @return the associated value
     * @throws RuntimeException if the key is not present
     */
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing configuration key: " + key);
        }
        return value;
    }

    /**
     * Returns the boolean value for the specified configuration key.
     *
     * @param key the configuration key
     * @return the parsed boolean value
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    /**
     * Returns the integer value for the specified configuration key.
     *
     * @param key the configuration key
     * @return the parsed integer value
     * @throws NumberFormatException if the value cannot be parsed as an integer
     */
    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    /**
     * Returns the active environment determined during initialization.
     *
     * @return the current environment
     */
    public static Environment getEnvironment() {
        return environment;
    }
}
