package com.uc.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if (extent==null) {
			
			//instantiate if not exist
			extent = new ExtentReports(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+"extent.html", true, DisplayOrder.OLDEST_FIRST);
			
			//configuration
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\resources\\extentconfig\\ReportsConfig.xml"));
			// more options available
			
		}
		return extent;
	}
}
