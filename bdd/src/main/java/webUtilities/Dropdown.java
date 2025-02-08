package webUtilities;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends Elements {

    private static final Logger logger = Logger.getLogger(Dropdown.class.getName());
    private By by;
    public Dropdown(By by, WebDriver driver) {
        super(by, driver);
        this.by = by;
    }

    public void selectByVisibleText(String text) {
        logger.log(Level.INFO, "Selecting dropdown option by text: ''{0}'' for element: {1}", new Object[]{text, by});
        Select dropdown = new Select(getElement());
        dropdown.selectByVisibleText(text);
    }
 
    public void selectByValue(String value) {
        logger.log(Level.INFO, "Selecting dropdown option by value: ''{0}'' for element: {1}", new Object[]{value, by});
        Select dropdown = new Select(getElement());
        dropdown.selectByValue(value);
    }
 
    public void selectByIndex(int index) {
        logger.log(Level.INFO, "Selecting dropdown option by index: {0} for element: {1}", new Object[]{index, by});
        Select dropdown = new Select(getElement());
        dropdown.selectByIndex(index);
    }
 
    public String getSelectedOption() {
        Select dropdown = new Select(getElement());
        String selectedText = dropdown.getFirstSelectedOption().getText();
        logger.log(Level.INFO, "Currently selected option for {0}: ''{1}''", new Object[]{by, selectedText});
        return selectedText;
    }
 
    public List<WebElement> getAllOptions() {
        Select dropdown = new Select(getElement());
        List<WebElement> options = dropdown.getOptions();
        logger.log(Level.INFO, "Retrieved {0} options from dropdown: {1}", new Object[]{options.size(), by});
        return options;
    }
 
    public boolean isMultiple() {
        Select dropdown = new Select(getElement());
        boolean multiple = dropdown.isMultiple();
        logger.log(Level.INFO, "Dropdown {0} allows multiple selections: {1}", new Object[]{by, multiple});
        return multiple;
    }
}
