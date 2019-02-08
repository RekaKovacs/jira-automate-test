package com.codecool.jira;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Issue {
    WebDriver driver;
    UtilJira util;
    String username = "user6";
    String pw = "CCPass123";
    List<String> listProjects;
    List<String> listIssueType;


    @BeforeEach
    public void initDriver() {

        this.util = UtilJira.getInstanceOfUtilJira();
        this.driver = util.initDriver();
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        util.logIn(username, pw);
        this.listProjects  = UtilJira.getListProjects();
    }

    @Test
    public void createIssueByButton() {
        driver.findElement(By.id("create_link")).click();
        boolean isThereDialogOpenClass = util.getWebelementByIdWaitPresence("create-issue-dialog").getAttribute("class").contains("jira-dialog-open");
        driver.findElement(By.id("create-issue-dialog")).findElement(By.className("cancel")).click();
        assertTrue(isThereDialogOpenClass);
    }

    @Test
    public void createIssueByProjectByTypes() throws InterruptedException {
        int counter = 0;

        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement createButton = driver.findElement(By.id("create_link"));
        createButton.click();

        for (String project : listProjects) {
            counter++;
            WebElement projectSelectInForm = wait.until(ExpectedConditions.elementToBeClickable(By.id("project-single-select"))).findElement(By.tagName("input"));
            projectSelectInForm.click();
            wait.until(ExpectedConditions.elementToBeClickable(projectSelectInForm)).sendKeys(Keys.BACK_SPACE);
            projectSelectInForm.sendKeys(project);
            wait.until(ExpectedConditions.textToBePresentInElement(projectSelectInForm, projectSelectInForm.getText()));
            projectSelectInForm.sendKeys(Keys.ENTER);

            WebElement issueTypeSelectInForm = wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
            issueTypeSelectInForm.click();
            wait.until(ExpectedConditions.elementToBeClickable(issueTypeSelectInForm)).sendKeys(Keys.BACK_SPACE);

            issueTypeSelectInForm.sendKeys("task");
            wait.until(ExpectedConditions.textToBePresentInElement(issueTypeSelectInForm, issueTypeSelectInForm.getText()));
            issueTypeSelectInForm.sendKeys(Keys.ENTER);

            WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
//            summary.click();
//            wait.until(ExpectedConditions.elementToBeClickable(projectSelectInForm)).sendKeys(Keys.BACK_SPACE);
            summary.sendKeys(counter + " KRK");
            wait.until(ExpectedConditions.textToBePresentInElement(summary, summary.getText()));

            wait.until(ExpectedConditions.elementToBeClickable(By.id("create-issue-submit"))).click();

        }

    }


    @AfterEach
    public void tearDown() {
        util.logOut();
        driver.close();
    }
}
