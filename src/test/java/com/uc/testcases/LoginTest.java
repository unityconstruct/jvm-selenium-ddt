package com.uc.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.uc.base.TestBase;

public class LoginTest extends TestBase {

	@Test
	public void loginAsBankManager() throws InterruptedException {
		log.debug("Inside Login Test");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(10000);
		log.debug("Inside succesfully executed");
	}
}
