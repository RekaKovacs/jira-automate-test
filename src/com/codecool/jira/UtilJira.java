package com.codecool.jira;

import org.junit.platform.commons.util.PackageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class UtilJira {
    private static final UtilJira INSTANCE = new UtilJira();
    private static  List<String> LISTPROJECTS;
    private static List<String> ISSUETYPE;

    WebDriver driver;
    WebDriverWait wDWait;

    private UtilJira() {
    }

    public static UtilJira getInstanceOfUtilJira() {
        return INSTANCE;
    }

    public static List<String> getListProjects() {
        LISTPROJECTS = new ArrayList<>();
        LISTPROJECTS.add("TOUCAN");
        LISTPROJECTS.add("JETI");
        LISTPROJECTS.add("COALA" );
        return LISTPROJECTS;
    }

    public static List<String> getListIssueType() {
        ISSUETYPE = new ArrayList<>();
        ISSUETYPE.add("BUG");
        ISSUETYPE.add("STORY");
        ISSUETYPE.add("TASK");
        return ISSUETYPE;
    }

    public WebDriver initDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver" );
        driver = new FirefoxDriver();
        this.wDWait = new WebDriverWait(driver, 30);
        return driver;
    }

    public void logIn(String username, String pw) {
        driver.findElement(By.id("user-options")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(pw);
        driver.findElement(By.id("login-form-submit")).click();
    }

    public void logOut() {
//        Wait wait = new FluentWait(driver)
//                .withTimeout(30, SECONDS)
//                .pollingEvery(5, SECONDS);

//        WebElement profilePicture = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("user-options")));
        WebElement profilePicture = wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-options")));
        profilePicture.click();

//        WebElement logOut = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("log_out")));
        WebElement logOut = wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("log_out")));
        logOut.click();
    }

    public String getDataUserValue() {
        Wait wait = new FluentWait(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS);

        WebElement messageError = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("header-details-user-fullname")));
//                wait.until(ExpectedConditions.presenceOfElementLocated(By.id("header-details-user-fullname")));
        return messageError.getAttribute("data-username");
    }

    public WebElement getWebelementByIdWaitPresence (String id) {
        WebElement webElement = wDWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        return webElement;
    }

    public Wait waitForPageToLoad() {
        Wait wait = new FluentWait(driver)

                .withTimeout(30, SECONDS)

                .pollingEvery(2, SECONDS)

                .ignoring(NoSuchElementException.class);
        return wait;
    }

}
