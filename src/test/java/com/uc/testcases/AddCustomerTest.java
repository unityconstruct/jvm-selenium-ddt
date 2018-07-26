package com.uc.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uc.base.TestBase;
import com.uc.utilities.ExcelReader;
import com.uc.utilities.TestUtil;



public class AddCustomerTest extends TestBase {

	//swap out for common data provider
	//@Test(dataProvider="getData")
//	@Test(dataProviderClass=TestUtil.class, dataProvider="dp2")
//	public void addCustomerTest3(String firstName, String lastName, String postCode, String alerttext) throws InterruptedException {
//		//Arrange
//		
//		//Act
//		click("addCustBtn_CSS");
//		type("firstname_CSS",firstName);
//		type("lastname_CSS", lastName);
//		type("postcode_CSS", postCode);
//		click("addBtn_CSS");
//		//Thread.sleep(2000);
//		//Assert
//		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//		Assert.assertTrue(alert.getText().contains(alerttext));
//		alert.accept();
//		//Assert.fail("Customer not added successfully");
//		//Thread.sleep(2000);
//	}
	
	
	@Test(dataProviderClass=TestUtil.class, dataProvider="dp3")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException {
		//Arrange
		
		//Act
		click("addCustBtn_CSS");
		type("firstname_CSS",data.get("firstname"));
		type("lastname_CSS", data.get("lastname"));
		type("postcode_CSS", data.get("postcode"));
		click("addBtn_CSS");
		//Thread.sleep(2000);
		//Assert
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();
		//Assert.fail("Customer not added successfully");
		//Thread.sleep(2000);
	}
}

