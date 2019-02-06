package com.codecool.jira;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Issue {
    WebDriver driver;
    UtilJira util;
    String username = "user3";
    String pw = "CCPass123";


    @BeforeEach
    public void initDriver() {

        this.util = UtilJira.getInstanceOfUtilJira();
        this.driver = util.initDriver();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        util.logIn(username, pw);
    }

    @Test
    public void createIssueByButton() {
        driver.findElement(By.id("create_link")).click();
        boolean isThereDialogOpenClass = util.getWebelementByIdWaitPresence("create-issue-dialog").getAttribute("class").contains("jira-dialog-open");
        driver.findElement(By.id("create-issue-dialog")).findElement(By.className("cancel")).click();
        assertTrue(isThereDialogOpenClass);
    }

    @AfterEach
    public void tearDown() {
        util.logOut();
        driver.close();
    }
}
