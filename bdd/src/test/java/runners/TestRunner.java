package runners;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinitions", "hooks" }, plugin = { "pretty",
		"html:target/cucumber-reports.html" }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) {
		System.setProperty("browser", browser); // Pass browser to Base.java
	}
}
