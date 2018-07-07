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
		log.debug("Inside Login Test");
		
		//Act
		//driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		
		click("bmlBtn");
		
		Thread.sleep(3000);
		
		//Assert
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login not successful");
		
		log.debug("Login succesfully executed");
		//Assert.assertTrue(false);
	}
	

}
