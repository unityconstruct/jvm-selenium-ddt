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
		click("addCustBtn_CSS");
		type("cusomer_CSS",customer);
		type("currency_CSS", currency);
		click("addBtn_CSS");
		
		//Assert
//		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//		Assert.assertTrue(alert.getText().contains(alerttext));
//		alert.accept();
		//Assert.assertTrue(false);
	}
}

 