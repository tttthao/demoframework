package com.abc.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditCustomerPage {
	WebDriver driver;
	public EditCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="cusid")
	WebElement txtCustomerId;
	
	@FindBy(name="AccSubmit")
	WebElement btnSubmit;
	
	@FindBy(name="res")
	WebElement btnReset;
	
	@FindBy(name="name")
	WebElement txtCustomerName;
	
	@FindBy(name="gender")
	WebElement txtGender;
	
	@FindBy(name="dob")
	WebElement txtDateOfBirth;
	
	
	
	public void enterCustomerId(String customerId) {
		txtCustomerId.sendKeys(customerId);
	}
	
	public void clickOnSubmit() {
		btnSubmit.click();
	}
	
	public String getCustomerName() {
		return txtCustomerName.getAttribute("value");
	}
	
	public String getGender() {
		return txtGender.getAttribute("value");
	}
	
	public String getDateOfBirth() {
		return txtDateOfBirth.getAttribute("value");
	}
}
