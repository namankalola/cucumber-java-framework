package swagLabs.stepDefinitions;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import swagLabs.pages.HomePage;
import swagLabs.pages.LoginPage;
import swagLabs.runners.TestRunner;
import utilities.ConfigManager;
import utilities.ExcelUtils;
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
        ExcelUtils.cleanup();
    }

    @Given("User is on Swag Labs login page")
    public void user_is_on_login_page() {
        String appUrl = configManager.getProperty("app.url");
        driver.get(appUrl); // Update URL
        loginPage = new LoginPage(driver);
    }

    @Given("I want to read test data from {string} sheet")
    public void I_want_to_read_test_data_from_sheet(String sheetName) {
        ExcelUtils.initialize(sheetName, configManager.getProperty("testdatafile"));
    }

    @When("User enters username {string} and password {string}")
    public void user_logs_in(String username, String password) {
        loginPage.enterUsername(ExcelUtils.getInstance().getFieldValue(username));
        loginPage.enterPassword(ExcelUtils.getInstance().getFieldValue(password));
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

    @When("This user has been locked out - message displayed")
    public void This_user_has_been_locked_out_message_displayed() {
        loginPage.lockedMessageDisplayed();
    }
}
