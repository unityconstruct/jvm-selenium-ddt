package com.uc.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uc.base.TestBase;
import com.uc.utilities.ExcelReader;
import com.uc.utilities.TestUtil;



public class OpenAccountTest extends TestBase {

	//swap out for common data provider
	//@Test(dataProvider="getData")
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp2")
	public void openAccountTest(String customer, String currency) throws InterruptedException {
		//Arrange
		
		//Act
		click("openaccount_CSS");
		select("customer_CSS",customer); 		//try using select class  
		select("currency_CSS",currency);		//try using select class 
		click("process_CSS");
		Thread.sleep(2000);
	
		//Assert
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		//Assert.assertTrue(alert.getText().contains(alerttext));
		alert.accept();
		//Assert.assertTrue(false);
	}
}

 