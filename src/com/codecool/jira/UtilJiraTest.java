package com.codecool.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

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

    public void logIn(String username, String pw) {
        driver.findElement(By.id("user-options")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login-form")).submit();
    }

    public void logOut() {
        Wait wait = new FluentWait(driver)

                .withTimeout(30, SECONDS)

                .pollingEvery(5, SECONDS)

                .ignoring(NoSuchElementException.class);

        WebElement profilePicture = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("user-options")));
        profilePicture.click();
        WebElement logOut = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("log_out")));
        logOut.click();
    }

}
