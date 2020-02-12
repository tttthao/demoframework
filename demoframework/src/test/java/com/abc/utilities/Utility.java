package com.abc.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class Utility {
	
	public static String getBrowser(String browser) {
		String br = System.getProperty("browser");
		if(br==null||br.isEmpty())
			return browser;
		return br;
	}
	
	public static WebDriver launchBrowserWithUrl(String browser, String url) throws Exception {
		Log.info("Testing environment: Browser="+browser+" and url=" + url);
		ConfigReader configReader = new ConfigReader();
		WebDriver driver = null;
		if(browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", configReader.getChromePath());
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options);	
			driver.get(url);
			
		}else if(browser.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", configReader.getFireFoxPath());
			FirefoxOptions options = new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(options);	
			driver.get(url);
			
		}else if(browser.equalsIgnoreCase("ie")) {
			
			System.setProperty("webdriver.ie.driver", configReader.getIEPath());
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
		String dateName = new SimpleDateFormat("yyyyMMdd.HHmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+screenshotName+"_"+dateName+".png";
		File target = new File(screenshotPath);
		FileUtils.copyFile(source, target);
		Log.info("Screenshot taken: " + screenshotPath);
		return screenshotPath;
	}
	
	public static String getRandomString(int numberOfChar) {
		return RandomStringUtils.randomAlphabetic(numberOfChar);
	}
	
	public static void closeAdvertisementPopup(WebDriver driver) {
		//Enter keyboard HOME to move to top page
		try {
			Robot rb = new Robot();
			rb.keyPress(KeyEvent.VK_HOME);
			rb.keyRelease(KeyEvent.VK_HOME);
		} catch (AWTException e) {
			Log.warn("Method[closeAdvertisementPopup]: Cannot press HOME");
		}
		
		//Wait until popup is show
		driver.switchTo().frame("flow_close_btn_iframe");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(Duration.ofSeconds(10))
			       .pollingEvery(Duration.ofSeconds(1))
			       .ignoring(NoSuchElementException.class)
			       .ignoring(TimeoutException.class);
		WebElement we = wait.until(new Function<WebDriver, WebElement>() {
		     public WebElement apply(WebDriver driver) {
		    	 return driver.findElement(By.id("closeBtn"));
		     }
	    });
		try {
			we.click();
		}catch(Exception e) {
			Log.warn("Method[closeAdvertisementPopup]: Popup is not showing to click");
		}
		driver.switchTo().defaultContent();
	}

}
