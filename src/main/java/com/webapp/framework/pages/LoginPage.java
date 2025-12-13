package com.webapp.framework.pages;

import org.openqa.selenium.By;

/**
 * LoginPage represents the login screen of the application.
 *
 * It encapsulates user interactions required to authenticate,
 * including entering credentials and submitting the login form.
 */
public class LoginPage extends BasePage {

    /** Username input field. */
    private final By usernameInput = By.name("username");

    /** Password input field. */
    private final By passwordInput = By.name("password");

    /** Login form submit button. */
    private final By loginButton = By.cssSelector("button[type='submit']");

    /** Branding logo used to verify login page visibility. */
    private final By brandLogo = By.cssSelector("img[alt='company-branding']");

    /**
     * Enters the provided username into the username field.
     *
     * @param username the username to enter
     * @return the current LoginPage instance for fluent chaining
     */
    public final LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    /**
     * Enters the provided password into the password field.
     *
     * @param password the password to enter
     * @return the current LoginPage instance for fluent chaining
     */
    public final LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    /**
     * Clicks the login button to submit the login form.
     */
    public final void clickLogin() {
        click(loginButton);
    }

    /**
     * Performs a complete login operation using the provided credentials.
     *
     * @param username the username
     * @param password the password
     */
    public void login(String username, String password) {
        LOG.info("Logging in as {}", username);
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Verifies whether the login page is currently displayed.
     *
     * @return true if the login page branding is visible, otherwise false
     */
    public boolean isLoginPageDisplayed() {
        return isDisplayed(brandLogo);
    }
}