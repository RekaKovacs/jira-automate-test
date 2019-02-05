package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    WebDriver driver;
    UtilJiraTest util;
    String username = "user3";
    String pw = "CCPass123";

    @BeforeEach
    public void initDriver() {

        this.util = UtilJiraTest.getUtilJiraTest();
        this.driver = util.iniTest();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    @Test
    public void loginBySidebar() {
        driver.findElement(By.id("user-options")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login-form-submit")).click();
    }

    @Test
    public void loginByDashboard() {
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login")).click();
    }

    @AfterEach
    public void tearDown() {
        util.logOut();
        driver.close();
    }
}
