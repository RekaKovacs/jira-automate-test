package com.codecool.jira;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Logout {
    WebDriver driver;
    UtilJira util;

    @BeforeEach
    public void initDriver() {

        this.util = UtilJira.getInstanceOfUtilJira();
        this.driver = util.initDriver();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    @Test
    public void logout() {
        util.logIn("user4", "CCPass123");
        util.logOut();
        assertEquals("Confirm logout - Jira", driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
        util.logOut();
        driver.close();
    }
}
