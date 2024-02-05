package Automation.testCases;
import Automation.pageObjects.InsiderPage;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;

import static Automation.utilities.Constants.OPEN_URL;
public class TC_Insider_Page_001 extends BaseClass {


    @Test(priority = 1)
    public void OpenUrl() throws IOException {
        InsiderPage gb = new InsiderPage(driver);

        driver.get(OPEN_URL);

        String openLinkText =gb.verifyPageTitle();

        // Check if the page is opened successfully
        if (openLinkText != null && !openLinkText.isEmpty()) {
            logger.info("Insider Home is opened");
            Assert.assertTrue(true);

        } else {
            // Page is not opened successfully
            logger.error("Insider Home is not opened successfully");
            Assert.fail("Insider Home is not opened successfully");
            captureScreen(driver,"OpenUrl");

        }
    }

}
