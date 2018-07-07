package com.uc.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uc.base.TestBase;
import com.uc.utilities.ExcelReader;



public class AddCustomerTest extends TestBase {

	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postCode, String alerttext) throws InterruptedException {
		//Arrange
		
		//Act
//		using the TestBase class methods for interacting with objects
//		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
//		driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
//		driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
//		driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
//		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
		
		click("addCustBtn");
		type("firstname",firstName);
		type("lastname", lastName);
		type("postcode", postCode);
		click("addBtn");
		
		//Assert
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alerttext));
		alert.accept();
		//Assert.assertTrue(false);
	}
	
	@DataProvider
	public Object[][] getData(){
		//TODO: creat enum for the sheet to use the ordinals
		String sheetName="AddCustomerTest";
		//int rows = excel.getRowCount(sheetName);
		int rows = 2;
		
		//int cols = excel.getColumnCount(sheetName);
		int cols = 4;
		
		System.out.println("worksheet rows/cols" + rows +"+"+cols);
		
		Object[][] data = new Object[rows-1][cols];
		
		//data[0][0] - since data is on row2... decrement by 2 to place at [0] in the array
		for(int rowNum=2;rowNum<=rows;rowNum++) { //2
			for(int colNum =0; colNum < cols; colNum++) {
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
	}
}


//manual assignment of the data		
//Object[][] data = new Object[rows-1][3];
//data[0][0] = excel.getCellData(sheetName, 0, 2);
//data[0][1] = excel.getCellData(sheetName, 1, 2);
//data[0][2] = excel.getCellData(sheetName, 2, 2);
//System.out.println(data.toString());
