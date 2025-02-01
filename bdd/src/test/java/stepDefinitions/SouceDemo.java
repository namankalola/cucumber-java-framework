package stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.LoginPage;
import runners.TestRunner;
import utilities.WebDriverManagerThread;

public class SouceDemo {

    private LoginPage loginPage;
    protected WebDriver driver;

	@Before
    public void setup() {
        String browserType = TestRunner.getBrowser();
        driver = WebDriverManagerThread.getInstance(browserType).getDriver();
        System.out.println("Started test on: " + browserType);
    }

    @After
    public void tearDown() {
        WebDriverManagerThread.clearThread();
    }

    @Given("User is on OrangeHRM login page")
    public void user_is_on_login_page() {
        driver.get("https://www.saucedemo.com/"); // Update URL
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
