package com.uc.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uc.base.TestBase;
import com.uc.utilities.ExcelReader;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postCode) {
		//
	}
	
	@DataProvider
	public Object[][] getData(){
		//TODO: creat enum for the sheet to use the ordinals
		String sheetName="AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		
		//int cols = excel.getColumnCount(sheetName);
		int cols = 3;
		
		System.out.println("worksheet rows/cols" + rows +"+"+cols);
		
		Object[][] data = new Object[rows-1][cols];
		
		//data[0][0] - since data is on row2... decrement by 2 to place at [0] in the array
		for(int rowNum=2;rowNum<=rows;rowNum++) { //2
			for(int colNum =0; colNum < cols; colNum++) {
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
//      manual assignment of the data		
//		Object[][] data = new Object[rows-1][3];
//		data[0][0] = excel.getCellData(sheetName, 0, 2);
//		data[0][1] = excel.getCellData(sheetName, 1, 2);
//		data[0][2] = excel.getCellData(sheetName, 2, 2);
//		System.out.println(data.toString());
		
		return data;
	}
}
