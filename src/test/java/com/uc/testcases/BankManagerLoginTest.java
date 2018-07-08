package com.uc.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.uc.base.TestBase;
import com.uc.utilities.TestUtil;


public class BankManagerLoginTest extends TestBase {

	//@Test
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp")
	public void loginAsBankManager() throws InterruptedException {
		//Arrange
		log.debug("Inside Login Test");
		//Act
		click("bmlBtn_CSS");
		//Assert
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successful");
		log.debug("Login succesfully executed");
		//Assert.assertTrue(false);
	}
	

}
