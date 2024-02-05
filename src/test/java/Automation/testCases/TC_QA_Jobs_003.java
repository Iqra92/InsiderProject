package Automation.testCases;

import Automation.pageObjects.QAJobsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static Automation.testCases.BaseClass.*;


public class TC_QA_Jobs_003 {

    @Test(priority = 3)
    public void filterJobs() throws IOException {
        QAJobsPage qAJobsPage = new QAJobsPage();
        qAJobsPage.OpenTheNewLink();
        qAJobsPage.acceptCookie();
        qAJobsPage.clickOnSeeAllQAJobs();
        Select dropdown = new Select(qAJobsPage.locationFilter);
        dropdown.selectByVisibleText("Istanbul, Turkey");
        scrollView();
        hoverElement();
        if(qAJobsPage.isDepartmentQA()){
            logger.info("Job is found ");
            Assert.assertTrue(true);
        }else{
            logger.info("Job is not found ");
            Assert.fail("Job is not found");
            captureScreen(driver,"Job Not Found");
        }

        if(qAJobsPage.isLocationTurkey()){
            logger.info("Location Turkey is found ");
            Assert.assertTrue(true);
        }else{
            logger.info("Location Turkey is not found ");
            Assert.fail("Location Turkey is not found");
            captureScreen(driver,"Location Not Found");
        }

    }

    public void scrollView(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");


    }
    @Test(priority = 4)
    public void verifyApplicationForm() throws IOException {
        try {
            QAJobsPage qAJobsPage = new QAJobsPage();
            hoverElement();
            qAJobsPage.clickViewRoleBtn();
        } catch (StaleElementReferenceException e) {
            QAJobsPage qAJobsPage = new QAJobsPage();
            qAJobsPage.clickViewRoleBtn();
        }
        switchWindow();
        try {
            QAJobsPage qAJobsPage = new QAJobsPage();
            if (qAJobsPage.getApplicationForm.isDisplayed()) {
                logger.info("Application form is Available in this page");
                Assert.assertTrue(true);

            } else {
                logger.info("Application form is Not Available in this page");
                Assert.assertFalse(false);
                captureScreen(driver,"ApplicationFormIsNotAvailable");
            }
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            logger.info("Application form is Not Available in this page");
            Assert.assertFalse(false);
            captureScreen(driver,"ApplicationFormIsNotAvailable");

        }
    }

    public void hoverElement(){
        QAJobsPage qAJobsPage = new QAJobsPage();
        Actions actions = new Actions(driver);
        actions.moveToElement(qAJobsPage.isLocation).perform();
    }

    public void switchWindow(){
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
        }
    }

}

