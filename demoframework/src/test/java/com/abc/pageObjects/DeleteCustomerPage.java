package com.abc.pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteCustomerPage {
	WebDriver driver;
	public DeleteCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="cusid")
	WebElement txtCustomerId;
	
	@FindBy(name="AccSubmit")
	WebElement btnSubmit;
	
	@FindBy(name="res")
	WebElement btnReset;
	
	public void enterCustomerId(String customerId) {
		txtCustomerId.sendKeys(customerId);
	}
	
	public void clickOnSubmit() {
		btnSubmit.click();
	}
	
	public String getAlertMessage() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public void confirmDeleteCustomer(String okOrCancel) {
		Alert alert = driver.switchTo().alert();
		if(okOrCancel.equalsIgnoreCase("ok")) {
			alert.accept();
		}else
			alert.dismiss();
	}

}
