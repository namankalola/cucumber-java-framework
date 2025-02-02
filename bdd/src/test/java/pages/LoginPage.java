package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import webUtilities.Button;
import webUtilities.Elements;
import webUtilities.TextBox;

public class LoginPage {
	private final WebDriver driver;

	private final By userNameLocator = By.xpath("//input[@id='user-name']");
	private final By passwordLocator = By.xpath("//input[@id='password']");
	private final By loginButtonLocator = By.xpath("//input[@id='login-button']");
	private final By loginPageLogo = By.xpath("//div[@class='login_logo']");

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

	public void loginPageDisplayed(){
		Assert.assertTrue(new Elements(loginPageLogo,driver).isDisplayed());
	}
}
