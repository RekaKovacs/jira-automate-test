package com.codecool.jira;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Login {
    WebDriver driver;
    UtilJiraTest util;

    @BeforeEach
    public void initDriver() {

        this.util = UtilJiraTest.getUtilJiraTest();
        this.driver = util.iniTest();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    @Test
    public void loginBySidebar() {
        String username = "user3";
        String pw = "CCPass123";
        driver.findElement(By.id("user-options")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login-form")).submit();
    }


}
