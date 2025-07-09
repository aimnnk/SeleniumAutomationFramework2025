package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.Log;

// inherit from basetest, logintest is the child
public class LoginTest extends BaseTest {

	// add test function
	@Test
	public void testValidLogin() {
		
		Log.info("Staring login test..");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
		
		System.out.println("Title of page is: " + driver.getTitle());
		Log.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
	}
}
