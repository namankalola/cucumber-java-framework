package webUtilities;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioButton extends Elements {
    private final By by;

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
