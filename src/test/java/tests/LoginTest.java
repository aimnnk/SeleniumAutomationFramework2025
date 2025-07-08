package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

// inherit from basetest, logintest is the child
public class LoginTest extends BaseTest {

	// add test function
	@Test
	public void testValidLogin() {
		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
		System.out.println("Title of page is: " + driver.getTitle());

		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
	}
}
