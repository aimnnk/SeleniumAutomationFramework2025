package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.Log;

public class LoginPage {

	private WebDriver driver;

	// element of the page
	private By usernameTextBox = By.id("Email");
	private By passwordTextBox = By.id("Password");
	private By loginButton = By.xpath("//*[@id=\"main\"]/div/div/div/div[2]/div[1]/div/form/div[3]/button");

	// constructor - function in the same class name
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// adding action function
	public void enterUsername(String username) {
		Log.info("Entering username: " + username);
		driver.findElement(usernameTextBox).clear();
		driver.findElement(usernameTextBox).sendKeys(username);
	}

	public void enterPassword(String password) {
		Log.info("Entering password: *****");
		driver.findElement(usernameTextBox).clear();
		driver.findElement(usernameTextBox).sendKeys("");
	}

	public void clickLogin() {
		
		Log.info("Clicking login button.");
		driver.findElement(loginButton).click();
	}
}
