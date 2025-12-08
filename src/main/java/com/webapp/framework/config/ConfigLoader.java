package com.webapp.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads application configuration properties from the classpath.
 *
 * <p>The loader determines which properties file to use based on the
 * system property {@code env}. If not provided, the default file
 * {@code config.properties} is used.</p>
 *
 * <p>This class provides utility methods to safely retrieve configuration
 * values with validation.</p>
 */
public final class ConfigLoader {

    private static final Properties properties = new Properties();

    // Prevent instantiation
    private ConfigLoader() {}

    static {
        String env = System.getProperty("env", "config");
        String filename = env + ".properties";

        try (InputStream input =
                     ConfigLoader.class.getClassLoader().getResourceAsStream(filename)) {

            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + filename);
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration: " + filename, e);
        }
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the configuration key to retrieve
     * @return the value associated with the key
     * @throws RuntimeException if the key does not exist
     */
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing configuration key: " + key);
        }
        return value;
    }

    /**
     * Returns the boolean value associated with the given key.
     *
     * @param key the configuration key to retrieve
     * @return the boolean value of the key, parsed using
     *         {@link Boolean#parseBoolean(String)}
     * @throws RuntimeException if the key does not exist
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
