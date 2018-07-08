package com.uc.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.uc.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenshot() throws IOException {
		//screencap & copy to report folder root
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		
		screenshotName = "error_" + d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\" + screenshotName));
	}

	
	// TODO: Ready for Method m logic for COMMON DATA PROVIDER
	@DataProvider(name="dp")
	public static Object[][] getData(){
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
