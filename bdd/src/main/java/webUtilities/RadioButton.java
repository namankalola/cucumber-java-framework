package webUtilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioButton extends Elements {

    private static final Logger logger = Logger.getLogger(RadioButton.class.getName());
    private By by;

    public RadioButton(By by, WebDriver driver) {
        super(by, driver);
        this.by = by;
    }

    public void select() {
        if (!isSelected()) {
            logger.log(Level.INFO, "Selecting radio button: ", by);
            getElement().click();
        } else {
            logger.log(Level.INFO, "Radio button already selected: ", by);
        }
    }

    public boolean isSelected() {
        boolean selected = getElement().isSelected();
        logger.log(Level.INFO, "Radio button state for {0}: {1}", new Object[]{by, selected});
        return selected;
    }
}
