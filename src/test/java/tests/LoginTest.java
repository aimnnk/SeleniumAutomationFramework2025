package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

// inherit from basetest, logintest is the child
public class LoginTest extends BaseTest {

	// add test function
	@Test
	public void testValidLogin() {

		Log.info("Staring login test..");
		test = ExtentReportManager.createTest("Login Test");

		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		test.info("Adding Credentials");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		test.info("Clicking on Login Button");
		loginPage.clickLogin();

		System.out.println("Title of page is: " + driver.getTitle());
		Log.info("Verifying page title");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
		test.pass("Login Successful");
	}
	
	@Test
	public void testLoginWithInvalidCredentials() {

		Log.info("Staring login test..");
		test = ExtentReportManager.createTest("Login Test With Invalid Credentials");

		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		test.info("Adding Credentials");
		loginPage.enterUsername("admin1234@yourstore.com");
		loginPage.enterPassword("admin123");
		test.info("Clicking on Login Button");
		loginPage.clickLogin();

		System.out.println("Title of page is: " + driver.getTitle());
		Log.info("Verifying page title");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login..123");
		test.pass("Login Successful");
	}
}
