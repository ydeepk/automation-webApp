package com.webapp.tests.smoke;


import com.webapp.framework.driver.DriverManager;
import com.webapp.framework.utils.WaitUtils;
import com.webapp.tests.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class orangeLoginPageTest extends BaseTest {

    @Test
    public void verifyLoginPageLoads() {
        By logo = By.cssSelector("img[alt='company-branding']");
        WaitUtils.waitForVisible(logo);
        LOG.info("OrangeHRM login page loaded successfully");
    }

}
