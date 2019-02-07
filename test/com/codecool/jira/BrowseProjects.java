package com.codecool.jira;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
            WebElement projectNameElementOnDetailedPage = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.partialLinkText(projectName)));
            String projectNameOnDetailedPage = projectNameElementOnDetailedPage.getText();
            if (projectNameOnDetailedPage.contains(projectName)) {
                workingProjectsDetailedPages++;
            }
        }
        assertEquals(projectNames.size(), workingProjectsDetailedPages);

    }

//    TODO: check filter for project types - NEEDS FINISHING!!!
    /*
    @Test
    public void testFilterOfProjectTypes() {
        Wait wait = util.waitForPageToLoad();
        driver.get("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
        WebElement ele = driver.findElement(By.xpath("//*[@id=\"browse-projects-sidebar\"]/nav/div/div[1]"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", ele);
        WebElement vmi = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.xpath("//*[@id=\"browse-projects-sidebar\"]/nav/div/div[1]")));

//        WebElement vmi = driver.findElement(By.cssSelector("div[class='project-types-filters']"));
        System.out.println(vmi);

//        WebElement vmi = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.xpath("//*[@id=\"browse-projects-sidebar\"]/nav/div/div[1]/ul")));
//        System.out.println(vmi);
        //        WebElement ele = driver.findElement(By.className("project-types-filters"));
//        WebElement industries = ele.findElement(By.cssSelector("div.columns.three.alpha > ul"));
        List<WebElement> types = vmi.findElement(By.tagName("ul")).findElements(By.tagName("li"));
        int numOfWorkingFilters = 0;
        for (WebElement type : types) {
            type.click();
            WebElement h2PageTitleElement = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.xpath("//*[@id=\"filter-projects\"]/div/h2")));
            String h2PageTitle = h2PageTitleElement.getText().toLowerCase();
            if (h2PageTitle.contains(type.getText().toLowerCase())) {
                numOfWorkingFilters++;
                System.out.println(numOfWorkingFilters);
            }
        }

//        WebElement projectTypesSidebar = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.id("browse-projects-sidebar")));
//        WebElement types = projectTypesSidebar.findElement(By.className("project-type-nav")).findElement(By.className("project-types-filters"));
//        driver.findElement(By.xpath("//*[@id=\"all-project-type\"]/a")).click();
//        WebElement listedProjects0 = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.xpath("//*[@id=\"filter-projects\"]/div/h2")));
//        driver.findElement(By.xpath("//*[@id=\"software-project-type\"]/a")).click();
//        WebElement listedProjects1 = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.xpath("//*[@id=\"filter-projects\"]/div/h2")));
//        driver.findElement(By.xpath("//*[@id=\"business-project-type\"]/a")).click();
//        WebElement listedProjects2 = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.xpath("//*[@id=\"filter-projects\"]/div/h2")));

//
//        List <WebElement> elementsOfProjectTypes = (List<WebElement>) wait.until((Function<WebDriver, List>) driver -> types.findElements(By.tagName("href")));
////        List<WebElement> elementsOfProjectTypes = projectTypes.findElements(By.tagName("li"));
//        System.out.println(elementsOfProjectTypes);
//        int numOfWorkingFilters = 0;
//        for (WebElement element : elementsOfProjectTypes) {
//            element.click();
//            WebElement listedProjects = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.xpath("//*[@id=\"filter-projects\"]/div/h2")));
//            if (listedProjects.getText().contains(element.getText())) {
//                numOfWorkingFilters++;
//                System.out.println(numOfWorkingFilters);
//            }
        }

    */

    @Test
    public void viewProjectReports() {
        List<String> projectNames = new ArrayList<>(Arrays.asList("COALA", "JETI", "TOUCAN"));
        int workingProjectsViewReports = 0;
        Wait wait = util.waitForPageToLoad();
        for (String projectName : projectNames) {
            driver.get("https://jira.codecool.codecanvas.hu/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all");
            WebElement projectDetailedPage = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.partialLinkText(projectName)));
            projectDetailedPage.click();

            WebElement ele = driver.findElement(By.cssSelector("span[title='Reports']"));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", ele);

            WebElement pageH1Title = (WebElement) wait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(By.className("subnavigator-title")));
            if (pageH1Title.getText().equals("All reports")) {
                workingProjectsViewReports++;
            }
        }
        assertEquals(projectNames.size(), workingProjectsViewReports);

    }
//    TODO: check filter for project categories
//    TODO: check search bar on all projects page
//    TODO: view a project's Glass Doku
//    TODO: view a project's releases



    @AfterEach
    public void tearDown() {
//        util.logOut();
        driver.close();
    }


}
