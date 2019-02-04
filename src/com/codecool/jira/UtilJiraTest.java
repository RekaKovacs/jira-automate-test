package com.codecool.jira;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UtilJiraTest {
    private static final UtilJiraTest INSTANCE = new UtilJiraTest();
    WebDriver driver;

    private UtilJiraTest() {
    }

    public static UtilJiraTest getUtilJiraTest() {
        return INSTANCE;
    }

    public WebDriver iniTest() {
        System.setProperty("webdriver.gecko.driver", "geckodriver" );
        driver = new FirefoxDriver();
        return driver;
    }

}
