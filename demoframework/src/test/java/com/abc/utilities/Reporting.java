package com.abc.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.abc.testCases.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentLogger;
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new Date());
		String reportName="TestReport_"+timeStamp+".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\"+ reportName);
		//htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+reportName);
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/Configurations/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "thaothai");
		
		htmlReporter.config().setDocumentTitle("Abc Test Project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
	
;	}
	
	public void onTestSuccess(ITestResult tr) {
		Log.info(tr.getName()+":PASSED");
		extentLogger = extent.createTest(tr.getName());
		extentLogger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		Log.error(tr.getName()+":FAILED:" + tr.getThrowable());
		String screenshotPath = null;
		try {
			screenshotPath = Utility.captureScreenshot(BaseClass.driver, tr.getName());
		}catch(Exception e) {
			Log.warn("Screenshot taken failed: " + e.getMessage());
		}
		
		extentLogger = extent.createTest(tr.getName());
		extentLogger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		extentLogger.log(Status.FAIL, MarkupHelper.createLabel(""+tr.getThrowable(), ExtentColor.RED));
		//String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f = new File(screenshotPath);
		if(f.exists()) {
			try {
				extentLogger.fail("Screenshot is below:"+extentLogger.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				Log.warn("Screenshot is not found: " + e.getMessage());
			}
		}	
	}
	
	public void onTestSkipped(ITestResult tr) {
		extentLogger = extent.createTest(tr.getName());
		extentLogger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.PINK));
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
	
}
