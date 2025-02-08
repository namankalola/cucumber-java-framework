package webUtilities;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBox extends Elements {
	private final By by;
	public TextBox(By by, WebDriver driver) {
		super(by, driver);
		this.by = by;
	}

	public void enterText(String text) {
		logger.log(Level.INFO, "Entering text: ''{0}'' into element: {1}", new Object[]{text, by});
		getElement().sendKeys(text);
	}

	public void clearText() {
		logger.log(Level.INFO, "Clearing text in element: {0}", by);
		getElement().clear();
	}

	public void clearTextUsingKeyboard() {
		WebElement webElement = getElement();
		logger.log(Level.INFO, "Clearing text in element: {0} using keyboard.", by);
		webElement.sendKeys(Keys.CONTROL, "a");
		webElement.sendKeys(Keys.DELETE);
		if (!webElement.getDomProperty("value").isEmpty()) {
			throw new RuntimeException("Failed to clear text in element: " + by);
		}
	}
}
