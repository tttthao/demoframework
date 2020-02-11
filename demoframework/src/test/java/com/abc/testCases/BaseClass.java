package com.abc.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.abc.utilities.ConfigReader;
import com.abc.utilities.Log;
import com.abc.utilities.Utility;


public class BaseClass {
	public static WebDriver driver;
	String AppUrl = "https://demo.guru99.com/v4/";

	@Parameters("Browser")
	@BeforeClass
	public void setup(String browser) throws Exception {
		Log.initLog(this.getClass().getName());
		Log.startLog(this.getClass().getName());
		driver = Utility.launchBrowserWithUrl(Utility.getBrowser(browser), new ConfigReader().getBaseUrl());
		//cmd run: mvn clean test -DtestngFile=Testsuit01.xml -Dbrowser=chrome
	}
	
	@AfterClass
	public void tearDown() {
		Log.endLog(this.getClass().getName());
		driver.quit();
	}
}
