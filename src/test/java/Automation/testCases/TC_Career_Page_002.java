package Automation.testCases;
import Automation.pageObjects.CareerPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Automation.testCases.BaseClass.*;

public class TC_Career_Page_002  {

    @Test(priority = 2)
    public void verifyCareerPage() throws IOException {
        CareerPage careerPage = new CareerPage();
        careerPage.clickCompany();
        careerPage.selectCareerOption();

        List<WebElement> locationList = careerPage.getLocationList();
        logger.info(locationList.get(0).getText());
        String locationText = locationList.get(0).getText();
        String[] lines = locationText.split("\\r?\\n");
        List<String> locationNames = new ArrayList<>(Arrays.asList(lines));
        if (locationNames.contains("New York")) {
            logger.info("TEST PASSED Location is Opened");
            Assert.assertTrue(true);
        }else{
            Assert.assertFalse(false);
            captureScreen(driver,"locationNotFound");
        }
        if (careerPage.getTeams().contains("Customer Success")) {
            logger.info("TEST PASSED Team is Opened");
            Assert.assertTrue(true);

        }else{
            Assert.assertFalse(false);
            captureScreen(driver,"TeamPageIsNotOpened");
        }
        if (!careerPage.getTeams().isEmpty()) {
            logger.info("TEST PASSED Customer Life at Insider Found");
            Assert.assertTrue(true);
        }else{
            Assert.assertFalse(false);
            captureScreen(driver,"TeamsEmpty");
        }
    }

}

