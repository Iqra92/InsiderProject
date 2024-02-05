package Automation.testCases;

import Automation.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static Automation.utilities.Constants.Base_URL;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	protected static WebDriver driver;

	public static Logger logger;

	@Parameters({"browser"})
	@BeforeClass
	public void setup(@Optional("defaultBrowser") String br) {
		logger = Logger.getLogger("Insider");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			option.addArguments("--disable-notifications");
			// option.addArguments("window-size=1920,1080");
			//option.addArguments("-incognito");
			option.addArguments("start-maximized");
			option.addArguments("disable-infobars");
			option.addArguments("--disable-extensions");
			// option.addArguments("--disable-gpu");
			option.addArguments("--disable-dev-shm-usage");
			option.addArguments("--no-sandbox");
			driver = new ChromeDriver(option);
		} else if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		} else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
			driver = new InternetExplorerDriver();
		}

		// Set implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {
			driver.get(Base_URL);

			// Check if the cookie pop-up is present
			WebElement cookiePopup = driver.findElement(By.id("CXQnmb"));

			if (cookiePopup.isDisplayed()) {
				// Accept cookies
				WebElement acceptCookiesButton = driver.findElement(By.id("L2AGLb"));
				acceptCookiesButton.click();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@AfterSuite
	public void tearDown()
	{

		driver.quit();
	}

	public static  void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}


}
