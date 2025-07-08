package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
		driver.findElement(usernameTextBox).clear();
		driver.findElement(usernameTextBox).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(usernameTextBox).clear();
		driver.findElement(usernameTextBox).sendKeys("");
	}

	public void clickLogin() {
		driver.findElement(loginButton).click();
	}
}
