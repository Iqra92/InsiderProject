package Automation.pageObjects;

import Automation.testCases.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class InsiderPage {


        public InsiderPage(WebDriver rdriver) {
            PageFactory.initElements(rdriver, this);
        }

    @FindBy(how = How.XPATH, using = "//div[@class='slide-text']")
    @CacheLookup
    WebElement getOpenLink;


    public String verifyPageTitle() {
            return getOpenLink.getText();
    }



}
