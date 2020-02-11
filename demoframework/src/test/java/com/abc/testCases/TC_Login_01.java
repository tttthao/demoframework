package com.abc.testCases;

import org.testng.annotations.Test;

import com.abc.pageObjects.LoginPage;
import com.abc.utilities.Log;

public class TC_Login_01 extends BaseClass{

	@Test
	public void TCLogin01() {	
		LoginPage lp = new LoginPage(driver);
		lp.login("mngr245325", "EnaqEmA");
		Log.info("Entered username and password!");
		System.out.println(driver.getTitle());
		Log.info("Done");
	}
}
