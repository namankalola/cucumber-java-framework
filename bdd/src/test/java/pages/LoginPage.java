package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import webUtilities.Button;
import webUtilities.TextBox;

public class LoginPage {
	private final WebDriver driver;

	private final By userNameLocator = By.xpath("//input[@id='user-name']");
	private final By passwordLocator = By.xpath("//input[@id='password']");
	private final By loginButtonLocator = By.xpath("//input[@id='login-button']");

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
