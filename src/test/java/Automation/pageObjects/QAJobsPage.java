package Automation.pageObjects;

import Automation.testCases.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static Automation.utilities.Constants.*;

public class QAJobsPage extends BaseClass {


    public QAJobsPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'See all QA jobs')]")
    @CacheLookup
    WebElement seeAllQAJobs;


    @FindBy(how = How.ID, using = "filter-by-location")
    @CacheLookup
    public
    WebElement locationFilter;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Quality Assurance')]")
    @CacheLookup
    WebElement isQaDepartment;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'Istanbul, Turkey')]")
    @CacheLookup
    public
    WebElement isLocation;

    @FindBy(how = How.XPATH, using = "//body/section[@id='career-position-list']/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]")
    @CacheLookup
    public
    WebElement viewRoleBtn;

    @FindBy(how = How.XPATH, using = "//h4[contains(text(),'Submit your application')]")
    @CacheLookup
    public
    WebElement getApplicationForm;

    @FindBy(how = How.ID, using = "cookie-law-info-bar")
    @CacheLookup
    public
    WebElement getCookie;

    @FindBy(how = How.ID, using = "wt-cli-accept-all-btn")
    @CacheLookup
    public
    WebElement acceptCookie;


    public void OpenTheNewLink() {
        driver.get(JOB_CAREER_LINK);
    }

    public void acceptCookie(){
        // Check if the cookie popup is present
        WebElement cookiePopup = null;
        try {
            cookiePopup = getCookie;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Cookie popup not found
        }

        // Handle the cookie popup if present
        if (cookiePopup != null) {
            // Interact with the elements to either accept or dismiss the cookies
            WebElement acceptButton = acceptCookie;
            acceptButton.click();
        }
    }

    public void clickOnSeeAllQAJobs() {
        seeAllQAJobs.click();
    }

    public boolean isDepartmentQA() {
        String value = String.valueOf(isQaDepartment);
        logger.info("name of job => " + value);

        if (value.contains(DEPARTMENT)) {
            return true;
        } else {
            return false;
        }
    }
        public boolean isLocationTurkey() {
            String value = String.valueOf(isLocation);
            logger.info("name of job => " + value);

            if (value.contains(LOCATION)) {
                return true;
            }
            else{
                return false;
            }



    }
    public void clickViewRoleBtn(){
        viewRoleBtn.click();
    }
}
