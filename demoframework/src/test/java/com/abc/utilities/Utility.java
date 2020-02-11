package com.abc.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class Utility {
	
	public static String getBrowser(String browser) {
		String br = System.getProperty("browser");
		if(br==null||br.isEmpty())
			return browser;
		return br;
	}
	
	public static WebDriver launchBrowserWithUrl(String browser, String url) throws Exception {
		Log.info("Testing environment: Browser="+browser+" and url=" + url);
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);	
			driver.get(url);
			
		}else if(browser.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(options);	
			driver.get(url);
			
		}else if(browser.equalsIgnoreCase("ie")) {
			
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.ignoreZoomSettings();
			driver = new InternetExplorerDriver(options);
			driver.get(url);
			try {
				driver.navigate ().to ("javascript:document.getElementById('overridelink').click()");
			}catch(Exception e) {
				
			}
			
		}else {
			throw new Exception("The Browser '"+ browser +" is not correct'. It is not 'chrome' or 'firefox' or 'ie'");
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public static String captureScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMdd.hhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+"_"+dateName+".png";
		File target = new File(screenshotPath);
		FileUtils.copyFile(source, target);
		Log.info("Screenshot taken: " + screenshotPath);
		return screenshotPath;
	}

}
