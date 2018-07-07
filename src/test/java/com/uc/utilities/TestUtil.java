package com.uc.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
}
