package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;

	// setup report function
	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}

	// setup testing
	@BeforeMethod
	public void setUp() {

		Log.info("Starting WebDriver..");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to URL..");
		driver.get("https://admin-demo.nopcommerce.com/login");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure");
			test.fail("Test Failed.. Check Screenshot.",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}

		if (driver != null) {

			Log.info("Closing Browser..");
			driver.quit();
		}
	}
}
