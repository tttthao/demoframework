package com.abc.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='menusubnav']//a[text()='Manager']")
	WebElement lnkManager;
	
	@FindBy(xpath="//ul[@class='menusubnav']//a[text()='New Customer']")
	WebElement lnkNewCustomer;
	
	@FindBy(xpath="//ul[@class='menusubnav']//a[text()='Edit Customer']")
	WebElement lnkEditCustomer;
	
	@FindBy(xpath="//ul[@class='menusubnav']//a[text()='Delete Customer']")
	WebElement lnkDeleteCustomer;
	
	@FindBy(xpath="//ul[@class='menusubnav']//a[text()='']")
	WebElement lnkLogout;
	
	public void clickonManager() {
		lnkManager.click();
	}
	
	public void clickonNewCustomer() {
		lnkNewCustomer.click();
	}
	
	public void clickonEditCustomer() {
		lnkEditCustomer.click();
	}
	
	public void clickonDeleteCustomer() {
		lnkDeleteCustomer.click();
	}
	
	public void clickonLogout() {
		lnkLogout.click();
	}

}
