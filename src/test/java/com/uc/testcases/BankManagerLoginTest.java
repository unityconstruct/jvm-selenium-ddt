package com.uc.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.uc.base.TestBase;


public class BankManagerLoginTest extends TestBase {

	@Test
	public void loginAsBankManager() throws InterruptedException {
		//Arrange

		
		// for hyperlinks in testng reports
		System.setProperty("org.uncommons.reportng.escape-output","false");
		
		
		
		log.debug("Inside Login Test");
		
		//Act
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		//Thread.sleep(3000);
		
		//Assert
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login not successful");
		log.debug("Login succesfully executed");
		Reporter.log("Login succesfully executed");
		Reporter.log("<a target=\"_blank\" href=\"C:\\a\\d\\capture\\2018.07.07.021102.cap.jpg\">Screenshot</a><br/>");
		Reporter.log("<a href=\"C:\\a\\d\\capture\\2018.07.07.021102.cap.jpg\"><img height=\"50%\" width=\"50%\" src=\"C:\\a\\d\\capture\\2018.07.07.021102.cap.jpg\"/></a>");
	}

	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
}
