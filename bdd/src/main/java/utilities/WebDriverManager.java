package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Developer: Namankumar Kalola
 * Date Created: October 16, 2024
 * Browser types supported:
 *  - "chrome"
 *  - "firefox"
 *  - "edge"
 */


public class WebDriverManager {
	private volatile static WebDriverManager instance;
	private static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

	private WebDriverManager() {
	}

	private void initializeWebDriver(String browserType) {
		switch (browserType) {
		case "chrome":
			threadDriver.set(new ChromeDriver());
			break;
		case "firefox":
			threadDriver.set(new FirefoxDriver());
			break;
		case "edge":
			threadDriver.set(new EdgeDriver());
			break;

		default:
			throw new IllegalArgumentException("Unknown web browser : " + browserType);
		}
	}

	public static WebDriverManager getInstance(String browserType) {
		if (instance == null) {
			synchronized (WebDriverManager.class) {
				instance = new WebDriverManager();
			}
		}
		if(threadDriver.get()==null) {
			instance.initializeWebDriver(browserType);
		}
		return instance;
	}

	public WebDriver getDriver() {
		return threadDriver.get();
	}
	
	public static void clearThread() {
		if(threadDriver.get()!=null) {
			threadDriver.get().quit();
			threadDriver.remove();
		}
	}
}
