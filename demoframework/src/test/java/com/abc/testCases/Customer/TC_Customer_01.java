package com.abc.testCases.Customer;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.abc.pageObjects.DeleteCustomerPage;
import com.abc.pageObjects.EditCustomerPage;
import com.abc.pageObjects.HomePage;
import com.abc.pageObjects.LoginPage;
import com.abc.pageObjects.NewCustomerPage;
import com.abc.testCases.BaseClass;
import com.abc.utilities.Log;
import com.abc.utilities.Utility;

public class TC_Customer_01 extends BaseClass{
	
	@Test
	public void TCCustomer01() {	
		LoginPage loginPg = new LoginPage(driver);
		HomePage homePg = new HomePage(driver);
		NewCustomerPage newCustomerPg = new NewCustomerPage(driver);
		EditCustomerPage editCustomerPg = new EditCustomerPage(driver);
		DeleteCustomerPage deleteCustomerPg = new DeleteCustomerPage(driver);
		
		Log.info("Login page with username="+configReader.getUsername()+", password="+ configReader.getPassword());
		loginPg.login(configReader.getUsername(), configReader.getPassword());
		
		Log.info("Close Advertisement Popup");
		Utility.closeAdvertisementPopup(driver);
		
		Log.info("Click on New Customer");
		homePg.clickonNewCustomer();
		
		Log.info("Close Advertisement Popup");
		Utility.closeAdvertisementPopup(driver);

		Log.info("Enter Customer Info");
		String customerName = Utility.getRandomString(10);
		String gender = "female";
		String dateOfBirth = "03/02/1999";
		String dateOfBirth2= "1999-03-02";
		newCustomerPg.enterCustomerName(customerName);
		newCustomerPg.selectGender(gender);
		newCustomerPg.enterDateOfBirth(dateOfBirth);
		newCustomerPg.enterAddress("35 Nguyen Thi Minh Khai");
		newCustomerPg.enterCity("HCM");
		newCustomerPg.enterState("HCM");
		newCustomerPg.enterPIN("888888");
		newCustomerPg.enterMobile("12345678");
		newCustomerPg.enterEmail(Utility.getRandomString(10)+"@gmail.com");
		newCustomerPg.enterPassword("12345678");
		newCustomerPg.clickOnSubmit();
		
		Log.info("Verify customer creating successfully");
		Assert.assertTrue(newCustomerPg.verifyCreateNewCustomerSuccessfully());
		
		Log.info("Get created customer ID");
		String customerID = newCustomerPg.getCreatedCustomerID();
		
		Log.info("Click on Edit Customer");
		homePg.clickonEditCustomer();
		
		Log.info("Enter customer id and click on Submit");
		editCustomerPg.enterCustomerId(customerID);
		editCustomerPg.clickOnSubmit();
		
		Log.info("Get customer name, then verify");
		Assert.assertEquals(editCustomerPg.getCustomerName(), customerName);
		
		Log.info("Get gender, then verify");
		Assert.assertEquals(editCustomerPg.getGender(), gender);
		
		Log.info("Get date of birth, then verify");
		Assert.assertEquals(editCustomerPg.getDateOfBirth(), dateOfBirth2);
		
		Log.info("Click on Delete Customer");
		homePg.clickonDeleteCustomer();
		
		Log.info("Enter Customer Id, then click on Submit to delete customer");
		deleteCustomerPg.enterCustomerId(customerID);
		deleteCustomerPg.clickOnSubmit();
		
		Log.info("Get delete confirm message, and verify");
		assertEquals(deleteCustomerPg.getAlertMessage(), "Do you really want to delete this Customer?");
		deleteCustomerPg.confirmDeleteCustomer("ok");
		assertEquals(deleteCustomerPg.getAlertMessage(), "Customer deleted Successfully");
		deleteCustomerPg.confirmDeleteCustomer("ok");
		
	}
}
