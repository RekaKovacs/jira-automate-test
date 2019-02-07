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

public class UtilJira {
    private static final UtilJira INSTANCE = new UtilJira();
    WebDriver driver;

    private UtilJira() {
    }

    public static UtilJira getInstanceOfUtilJira() {
        return INSTANCE;
    }

    public WebDriver initDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver" );
        driver = new FirefoxDriver();
        return driver;
    }

    public void logIn(String username, String pw) {
        driver.findElement(By.id("user-options")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login-form-submit")).click();
    }

    public void logOut() {
        Wait wait = waitForPageToLoad();

        WebElement profilePicture = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("user-options")));
        profilePicture.click();
        WebElement logOut = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("log_out")));
        logOut.click();
    }

    public Wait waitForPageToLoad() {
        Wait wait = new FluentWait(driver)

                .withTimeout(30, SECONDS)

                .pollingEvery(2, SECONDS)

                .ignoring(NoSuchElementException.class);
        return wait;
    }

}
