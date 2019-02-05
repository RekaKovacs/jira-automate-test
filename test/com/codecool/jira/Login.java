package com.codecool.jira;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Login {
    WebDriver driver;
    UtilJira util;

    @Test
    public void initDriver() {

        this.util = UtilJira.getInstanceOfUtilJira();
        this.driver = util.initDriver();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

}
