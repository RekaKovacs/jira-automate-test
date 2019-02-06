package com.codecool.jira;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.*;

public class Login {
    WebDriver driver;
    UtilJira util;
    String username = "user3";
    String pw = "CCPass123";


    @BeforeEach
    public void initDriver() {

        this.util = UtilJira.getInstanceOfUtilJira();
        this.driver = util.initDriver();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
    }

    @Test
    public void loginBySidebar() {
        driver.findElement(By.id("user-options")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login-form-submit")).click();
        assertEquals(username, util.getDataUserValue());
    }

    @Test
    public void loginByDashboard() {
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login")).click();
        assertEquals(username, util.getDataUserValue());
    }


    @Test
    public void loginEmptyCredentials() {
        util.logIn("", "");
        Boolean resultTrueFalse = driver.findElement(By.className("error")).isEnabled();
        assertEquals(true, resultTrueFalse);
    }

    @Test
    public void loginByWrongPassword() {
        util.logIn(username, "cCPass123");
        Boolean resultTrueFalse = driver.findElement(By.className("error")).isEnabled();
        assertEquals(true, resultTrueFalse);
    }
    
    @Test
    public void loginThreeTimesByWrongPassword() {
        for (int i = 0; i < 4; i++) {
            util.logIn("user6", "cCPass123");
        }

        Boolean resultTrueFalse = driver.findElement(By.id("captcha")).isEnabled();
        assertEquals(true, resultTrueFalse);
    }

    @AfterEach
    public void tearDown() {
        try {
            util.logOut();
        } catch (NoSuchElementException e) {

        } finally {
            driver.close();
        }


    }
}
