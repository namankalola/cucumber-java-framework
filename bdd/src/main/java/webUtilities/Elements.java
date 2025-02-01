package webUtilities;

import java.time.Duration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.LoggerManager;

public class Elements {
	private final WebDriver driver;
	private final By by;
	protected static final Logger logger = LoggerManager.getLogger();

	public Elements(By by, WebDriver driver) {
		this.by = by;
		this.driver = driver;
	}

	public void click() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
			logger.log(Level.INFO, "Clicking element: {0}", by);
			element.click();
		} catch (Exception e) {
			throw new RuntimeException("Element not clickable: " + by, e);
		}
	}

	public String getText() {
		logger.log(Level.INFO, "Getting text from element: {0}", by);
		return getElement().getText();
	}

	public boolean isDisplayed() {
		logger.log(Level.INFO, "Checking if element is displayed: {0}", by);
		try {
			return getElement().isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean waitForElementToDisappear(Duration timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			throw new RuntimeException("Element did not disappear within timeout: " + by, e);
		}
	}

	protected WebElement getElement(Duration timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			throw new RuntimeException("Element not found or not visible: " + by, e);
		}
	}

	protected WebElement getElement() {
		return getElement(Duration.ofSeconds(10)); // Default timeout
	}

	protected boolean isEnabled() {
		logger.log(Level.INFO, "Checking if element is Enabled: {0}", by);
		try {
			return getElement().isEnabled();
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<WebElement> getElementList(Duration timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}
}
