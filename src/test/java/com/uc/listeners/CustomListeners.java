package com.uc.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.uc.base.TestBase;
import com.uc.utilities.TestUtil;

//adding TestBase features
public class CustomListeners extends TestBase implements ITestListener 	{

	@Override
	public void onTestStart(ITestResult arg0) {
		test = rep.startTest(arg0.getName().toUpperCase());
		//runmode - Y
		if(!TestUtil.isTestRunnable(arg0.getName(), excel)) {
			// TestNG logging ( extentReports logging will be in the onTestSkipped() method
			throw new SkipException("Skipping the test "+ arg0.getName().toUpperCase()+" as the Runmode is N");
		}
	}
 
	@Override
	public void onTestSuccess(ITestResult arg0) {
		//get the passed test case name from TestBase.test for the message
		TestBase.test.log(LogStatus.PASS, arg0.getName().toUpperCase() +": PASS");
		// end the test
		rep.endTest(test);
		//flush the reports to disk
		rep.flush();
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// for hyperlinks in testng reports
		System.setProperty("org.uncommons.reportng.escape-output","false");
		TestUtil.captureScreenshot();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//on failure log the failure to extent, then pass on the throwed exception
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with exception : " + arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		rep.endTest(test);
		rep.flush();
		
		//Show image link & image
		Reporter.log("Capturing Screenshot");
		Reporter.log("<a target=\"_blank\" href=\"" + TestUtil.screenshotName +"\">Screenshot</a><br/>");
		Reporter.log("<a href=\""+TestUtil.screenshotName+"\"><img height=\"50%\" width=\"50%\" src=\""+TestUtil.screenshotName +"\"/></a>");
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase() + " : Skipped the test as the Runmode is N");
		rep.endTest(test);
		rep.flush();
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
