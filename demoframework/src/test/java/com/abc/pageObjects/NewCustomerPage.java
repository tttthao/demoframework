package com.abc.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.abc.utilities.Log;

public class NewCustomerPage {
	WebDriver driver;
	public NewCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="name")
	WebElement txtCustomerName;
	
	@FindBy(name="rad1")
	List<WebElement> radGender;
	
	@FindBy(id="dob")
	WebElement txtDateOfBirth;
	
	@FindBy(name="addr")
	WebElement txtAddress;
	
	@FindBy(name="city")
	WebElement txtCity;
	
	@FindBy(name="state")
	WebElement txtState;
	
	@FindBy(name="pinno")
	WebElement txtPin;
	
	@FindBy(name="telephoneno")
	WebElement txtMobileNumber;
	
	@FindBy(name="emailid")
	WebElement txtEmail;
	
	@FindBy(name="password")
	WebElement txtPassword;
	
	@FindBy(name="sub")
	WebElement btnSubmit;
	
	@FindBy(name="res")
	WebElement btnReset;
	
	@FindBy(xpath="//td[text()='Customer ID']/following-sibling::td")
	WebElement lblCustomerID;
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void enterCustomerName(String customerName) {
		txtCustomerName.sendKeys(customerName);
	}
	
	public void selectGender(String maleOrFemale) {
		for(WebElement e:radGender) {
			if(e.getAttribute("value").equalsIgnoreCase(""+maleOrFemale.charAt(0))) {
				e.click();
				break;
			}
		}
	}
	
	public void enterDateOfBirth(String dateOfBirth) {
		txtDateOfBirth.sendKeys(dateOfBirth);
	}
	
	public void enterAddress(String address) {
		txtAddress.sendKeys(address);
	}
	
	public void enterCity(String city) {
		txtCity.sendKeys(city);
	}
	
	public void enterState(String state) {
		txtState.sendKeys(state);
	}
	
	public void enterPIN(String pin) {
		txtPin.sendKeys(pin);
	}
	
	public void enterMobile(String mobileNumber) {
		txtMobileNumber.sendKeys(mobileNumber);
	}
	
	public void enterEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void enterPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickOnSubmit() {
		btnSubmit.click();
	}
	
	public void clickOnReset() {
		btnReset.click();
	}

	public boolean verifyCreateNewCustomerSuccessfully() {
		if(driver.getPageSource().contains("Customer Registered Successfully!!!")) {
			return true;
		}else return false;
	}
	
	public String getCreatedCustomerID() {
		Log.info("Method [getCreatedCustomerID]: Customer ID is " + lblCustomerID.getText());
		return lblCustomerID.getText();
	}
}
