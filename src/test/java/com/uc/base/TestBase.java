package com.uc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.uc.utilities.ExcelReader;
import com.uc.utilities.ExtentManager;
import com.uc.utilities.TestUtil;

public class TestBase {
	/*
	 * Initialize
	 * WebDriver - geckodriver, chromedriver, ieserverdriver, 
	 * Properties - Config.properties, OR.properties
	 * Logs - log4j jar, .log, log4j.properties, Logger class
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 * ReportNG, ExtentReports
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");// standard name for the logger
	//public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xls");
	public static ExcelReader excel;
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test; //define logs inside defined Extent Test cases

	// called before tests
	@BeforeSuite
	public void setUp() {
		log.debug("Begin setup()");
		
		try {
			excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("Config file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (config.getProperty("browser").equals("firefox")) {
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\executables\\geckodriver-v0.20.1-win32\\geckodriver.exe");
				
				// TODO: needs profile loaded 
				//System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"false");
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"C:\\temp\\selenium.log");
				
				driver = new FirefoxDriver();
				System.out.println("Firefox: " + System.getProperty("webdriver.gecko.driver"));
				log.debug("Firefox launched!");
			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\executables\\chromedriver-2.39-win32\\chromedriver.exe");
				driver = new ChromeDriver();
				System.out.println("Chrome: " + System.getProperty("webdriver.chrome.driver"));
				log.debug("Chrome launched!");
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")
						+ "\\src\\test\\resources\\executables\\iedriver\\IEDriverServer-3.13.0-win32\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				System.out.println("IE: " + System.getProperty("webdriver.ie.driver"));
				log.debug("IE launched!");
			}

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to: " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,5);
		}

	}

	// called after tests
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("Test execution completed");
	}
	
	public void click(String locator) {
		
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if ( locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		
		test.log(LogStatus.INFO, "Clicking on " + locator);
	}
	
	public void type(String locator, String value) {
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if ( locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing into " + locator + " as value: " + value);
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	static WebElement dropdown;
	
	public void select(String locator,String value) {
		//get the webelement
		if(locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if ( locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		
		//then create a select object for interacting
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selecting from dropdown " + locator + " value as: " + value);
		
	}
	
	
	public static void verifyEquals(String expected, String actual) {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			//ReportNG
			Reporter.log("<br/>"+"Verification failure : " + t.getMessage()+"<br/>");
			//Reporter.log("<a target=\"_blank\" href=\"" + TestUtil.screenshotName +"\">Screenshot</a><br/>");
			Reporter.log("<a target=\"_blank\" href=\""+TestUtil.screenshotName+"\"><img height=\"50%\" width=\"50%\" src=\""+TestUtil.screenshotName +"\"/></a>");
			Reporter.log("<br/>");
			//ExtentReports
			test.log(LogStatus.FAIL, "Verification failed with exception: " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));			
		}
		
	}

}
