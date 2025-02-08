package webUtilities;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkbox extends Elements {

    // protected static final Logger logger/ = LoggerManager.getLogger();
    private final By by;
    public Checkbox(By by, WebDriver driver) {
        super(by, driver);
        this.by = by;
    }

    public void toggle() {
        logger.log(Level.INFO, "Toggling checkbox: ", by);
        getElement().click();
    }

    public void select() {
        if (!isSelected()) {
            logger.log(Level.INFO, "Selecting checkbox: ", by);
            getElement().click();
        } else {
            logger.log(Level.INFO, "Checkbox already selected: ", by);
        }
    }

    public void deselect() {
        if (isSelected()) {
            logger.log(Level.INFO, "Deselecting checkbox: ", by);
            getElement().click();
        } else {
            logger.log(Level.INFO, "Checkbox already deselected: ", by);
        }
    }

    public boolean isSelected() {
        boolean selected = getElement().isSelected();
        logger.log(Level.INFO, "Checkbox state for {0}: {1}", new Object[]{by, selected});
        return selected;
    }
}
