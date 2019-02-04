package com.codecool.jira;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Login {
    WebDriver driver;
    UtilJiraTest util;

    @Test
    public void initDriver() {

        this.util = UtilJiraTest.getUtilJiraTest();
        this.driver = util.iniTest();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

}
