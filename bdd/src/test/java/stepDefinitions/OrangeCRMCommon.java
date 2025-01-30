package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;
import webUtilities.Base;

public class OrangeCRMCommon extends Base {
	private LoginPage loginPage;

	@Given("User is on OrangeHRM login page")
	public void user_is_on_login_page() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"); // Update URL
		loginPage = new LoginPage(driver);
	}
	
    @When("User enters username {string} and password {string}")
    public void user_logs_in(String username, String password) {
    	loginPage.enterUsername(username);
    	loginPage.enterPassword(password);
    }
    
    @And("User clicks on Login button")
    public void user_clicks_on_login_button() {
    	loginPage.clickLogin();
    }
}
