package com.uc.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.DataProvider;

import com.uc.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() {
		try {
			//screencap & copy to report folder root
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			Date d = new Date();
			
			screenshotName = "error_" + d.toString().replace(":", "_").replace(" ", "_")+".jpg";
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\" + screenshotName));
		} catch (WebDriverException | IOException e) {
			log.debug("Screenshot failed: " + e.getMessage());
		}
	}

	

//	@DataProvider(name="dp")
//	public static Object[][] getData(){
//		//TODO: create enum for the sheet to use the ordinals
//		String sheetName="AddCustomerTest";
//		//int rows = excel.getRowCount(sheetName);
//		int rows = 2;
//		
//		//int cols = excel.getColumnCount(sheetName);
//		int cols = 4;
//		
//		System.out.println("worksheet rows/cols" + rows +"+"+cols);
//		
//		Object[][] data = new Object[rows-1][cols];
//		
//		//data[0][0] - since data is on row2... decrement by 2 to place at [0] in the array
//		for(int rowNum=2;rowNum<=rows;rowNum++) { //2
//			for(int colNum =0; colNum < cols; colNum++) {
//				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//			}
//		}
//		return data;
//	}
	

	
	
	
	
	
	// DataProvider with HashTable
	@DataProvider(name="dp3")
	public static Object[][] getData3(Method m){
		//TODO: create enum for the sheet to use the ordinals
		
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		//int rows = 2;
		
		int cols = excel.getColumnCount(sheetName);
		//int cols = 4;
		
		System.out.println("worksheet rows/cols" + rows +"+"+cols);
		
		
		//set returned object for multiple rows & 1 column
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String,String> table = null;		
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String,String>();
			for (int colNum=0;colNum < cols; colNum++) {
				//data[0][0]
				//get headers from ROW1, each column
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				
				//data[table][0] = 'single column array with each row 'cell' containing the HashTable [with Header,Value pairs]
				data[rowNum-2][0] = table;
			}
		}
		return data;
	}	
	
	
	
	// TODO: Ready for Method m logic for COMMON DATA PROVIDER
	@DataProvider(name="dp2")
	public static Object[][] getData2(Method m){
		//TODO: create enum for the sheet to use the ordinals
		
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		//int rows = 2;
		
		int cols = excel.getColumnCount(sheetName);
		//int cols = 4;
		
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


	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName="test_suite";
		int rows = excel.getRowCount(sheetName);
		
		for(int rNum = 2; rNum<=rows; rNum++) {
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			
			if(testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y")) {
					return true; // will break out of method
				} else 
					return false;
			}
		}
		return false;
	}

}
