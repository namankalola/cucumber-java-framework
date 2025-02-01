package runners;

import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions", "hooks" }, plugin = { "pretty",
		"html:target/cucumber-reports.html" }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {
	private static final ThreadLocal<String> browser = new ThreadLocal<>();
	
	 @BeforeTest
    public void setBrowser(ITestContext context) {
        String testBrowser = context.getCurrentXmlTest().getParameter("browser");
        browser.set(testBrowser);
    }

    public static String getBrowser() {
        return browser.get() != null ? browser.get() : "chrome"; // Default to Chrome
    }
}
