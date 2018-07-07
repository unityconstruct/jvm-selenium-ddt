package com.uc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

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

	// called before tests
	@BeforeSuite
	public void setUp() {
		log.debug("Begin setup()");
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
}
