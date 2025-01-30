package webUtilities;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.WebDriverManagerThread;

public class Base {
    protected WebDriver driver;

    @Before
    public void setup() {
        String browserType = System.getProperty("browser", "chrome"); // Default to Chrome
        driver = WebDriverManagerThread.getInstance(browserType).getDriver();
    }

    @After
    public void tearDown() {
        WebDriverManagerThread.clearThread();
    }
}
