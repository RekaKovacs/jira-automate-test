package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UtilJira {
    private static final UtilJira INSTANCE = new UtilJira();
    WebDriver driver;

    private UtilJira() {
    }

    public static UtilJira getInstanceOfUtilJira() {
        return INSTANCE;
    }

    public WebDriver initDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver" );
        driver = new FirefoxDriver();
        return driver;
    }


}
