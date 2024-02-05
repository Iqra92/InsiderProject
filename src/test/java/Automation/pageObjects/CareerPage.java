package Automation.pageObjects;

import Automation.testCases.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareerPage extends BaseClass {

    public CareerPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Company')]")
    @CacheLookup
    WebElement companyMenu;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Careers')]")
    @CacheLookup
    WebElement careersOption;

    @FindBy(how = How.CLASS_NAME, using = "glide__slides")
    @CacheLookup
    List<WebElement> locationList;

    @FindBy(how = How.XPATH, using = "//h3[contains(text(),'Customer Success')]")
    @CacheLookup
    WebElement teamsBlock;

    @FindBy(how = How.XPATH, using = "//h2[normalize-space()='Life at Insider']")
    @CacheLookup
    WebElement lifeAtInsiderBlock;

    public void clickCompany()
    {
        companyMenu.click();
    }

    public void selectCareerOption()
    {
        careersOption.click();
    }

    public List<WebElement> getLocationList() {
        return locationList;
    }
    public String getTeams()
    {
        return teamsBlock.getText();
    }
    public String getLifeAtInsider()
    {
    return lifeAtInsiderBlock.getText();
    }
}
