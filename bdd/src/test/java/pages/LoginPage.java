package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import webUtilities.Button;
import webUtilities.TextBox;

public class LoginPage {
	private WebDriver driver;

	private By userNameLocator = By.xpath("//input[@placeholder='Username']");
	private By passwordLocator = By.xpath("//input[@placeholder='Password']");
	private By loginButtonLocator = By.xpath("//button[@type='submit']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		new TextBox(userNameLocator, driver).enterText(username);
	}

	public void enterPassword(String password) {
		new TextBox(passwordLocator, driver).enterText(password);
	}

	public void clickLogin() {
		new Button(loginButtonLocator, driver).click();
	}
}
