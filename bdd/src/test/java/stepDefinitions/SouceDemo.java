package stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import runners.TestRunner;
import utilities.ConfigManager;
import utilities.WebDriverManager;

public class SouceDemo {

    // Webpages class
    private LoginPage loginPage;
    private HomePage homePage;
    protected WebDriver driver;
    private ConfigManager configManager;

    @Before
    public void setup() {
        String browserType = TestRunner.getBrowser();
        driver = WebDriverManager.getInstance(browserType).getDriver();
        System.out.println("Started test on: " + browserType);
        configManager = new ConfigManager();
        configManager.loadProperties(TestRunner.getEnvironment());
    }

    @After
    public void tearDown() {
        WebDriverManager.clearThread();
    }

    @Given("User is on Swag Labs login page")
    public void user_is_on_login_page() {
        String appUrl = configManager.getProperty("app.url");
        driver.get(appUrl); // Update URL
        loginPage = new LoginPage(driver);
    }

    @When("User enters username {string} and password {string}")
    public void user_logs_in(String username, String password) {
        loginPage.enterUsername(configManager.getProperty(username));
        loginPage.enterPassword(configManager.getProperty(password));
    }

    @And("User clicks on Login button")
    public void user_clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @And("Products page displayed")
    public void product_page_displayed() {
        homePage = new HomePage(driver);
        homePage.productHomePageDisplayed();
    }

    @When("User clicks on humbergur menu and Logout link")
    public void user_logout() {
        homePage.clickHumburger();
        homePage.clickLogout();
    }

    @And("Swag Labs Login page displayed")
    public void login_page_displayed() {
        loginPage.loginPageDisplayed();
    }
}
