package webUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button extends Elements {
	public Button(By by, WebDriver driver) {
		super(by, driver);
	}
	
	public void clickButton() {
		getElement().click();
	}
	
}
