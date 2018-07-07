package com.uc.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.uc.utilities.TestUtil;

public class CustomListeners implements ITestListener 	{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
 
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		// for hyperlinks in testng reports
		System.setProperty("org.uncommons.reportng.escape-output","false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Show image link & image
		Reporter.log("Capturing Screenshot");
		Reporter.log("<a target=\"_blank\" href=\"" + TestUtil.screenshotName +"\">Screenshot</a><br/>");
		Reporter.log("<img height=\"50%\" width=\"50%\" src=\""+TestUtil.screenshotName +"\"/>");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

		
}
