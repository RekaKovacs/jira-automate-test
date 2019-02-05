package com.codecool.jira;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseProjects {

    WebDriver driver;
    UtilJira util;
    String username = "user4";
    String password = "CCPass123";

    @BeforeEach
    public void initDriverAndLogin() {

        this.util = UtilJira.getInstanceOfUtilJira();
        this.driver = util.initDriver();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        util.logIn(username, password);
    }

    @Test
    public void viewAllProjects() {

        driver.findElement(By.linkText("Projects")).click();
        driver.findElement(By.linkText("View All Projects")).click();
        assertEquals("Browse projects - Jira", driver.getTitle());
    }

    @Test
    public void detailedPageOfProject() {
        List<String> projectNames = new ArrayList<>(Arrays.asList("COALA", "JETI", "TOUCAN"));
        int workingProjectsDetailedPages = 0;
        for (String projectName : projectNames) {
            driver.findElement(By.linkText("Projects")).click();
            Wait wait = util.waitForPageToLoad();
            WebElement allProjects = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.linkText("View All Projects")));
            allProjects.click();
            WebElement projectDetailedPage = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.partialLinkText(projectName)));
            projectDetailedPage.click();
            if (driver.findElement(By.partialLinkText(projectName)).isDisplayed()) {
                workingProjectsDetailedPages++;
            }
        }
        assertEquals(projectNames.size(), workingProjectsDetailedPages);

    }

    @AfterEach
    public void tearDown() {
//        util.logOut();
        driver.close();
    }


}
