package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

// inherit from basetest, logintest is the child
public class LoginTest extends BaseTest {

	// to implement data-driven testing (excel/csv)
	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {

		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {

			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
		}
		ExcelUtils.closeExcel();
		return data;
	}
	
	// get data from a data provider method
	@DataProvider(name="LoginData2")
	public Object[][] getData(){
		return new Object[][] {
			{"user1", "pass1"},
			{"user2", "pass2"},
			{"admin@yourstore.com", "admin"}
		};
	}

	// add test function
//	@Test(dataProvider = "LoginData2")
	
	@Test
	@Parameters({"username", "password"})
	public void testValidLogin(String username, String password) {

		Log.info("Staring login test..");
		test = ExtentReportManager.createTest("Login Test - " + username);

		test.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding credentials");
		test.info("Adding Credentials");

		// hardcoded
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");

		// get from excel sheet
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		test.info("Clicking on Login Button");
		loginPage.clickLogin();

		System.out.println("Title of page is: " + driver.getTitle());
		Log.info("Verifying page title");
		test.info("Verifying page title");
		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
		test.pass("Login Successful");
	}

//	@Test
//	public void testLoginWithInvalidCredentials() {
//
//		Log.info("Staring login test..");
//		test = ExtentReportManager.createTest("Login Test With Invalid Credentials");
//
//		test.info("Navigating to URL");
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Adding credentials");
//		test.info("Adding Credentials");
//		loginPage.enterUsername("admin1234@yourstore.com");
//		loginPage.enterPassword("admin123");
//		test.info("Clicking on Login Button");
//		loginPage.clickLogin();
//
//		System.out.println("Title of page is: " + driver.getTitle());
//		Log.info("Verifying page title");
//		test.info("Verifying page title");
//		Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login..123");
//		test.pass("Login Successful");
//	}
}
