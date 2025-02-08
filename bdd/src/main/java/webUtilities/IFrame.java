package webUtilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IFrame {

    private static final Logger logger = Logger.getLogger(IFrame.class.getName());
    private final WebDriver driver;
    private final By iframeLocator;

    public IFrame(By iframeLocator, WebDriver driver) {
        this.iframeLocator = iframeLocator;
        this.driver = driver;
    }
 
    public void switchToIFrame() {
        try {
            WebElement iframeElement = driver.findElement(iframeLocator);
            driver.switchTo().frame(iframeElement);
            logger.log(Level.INFO, "Switched to iframe: {0}", iframeLocator);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to switch to iframe: {0}. Exception: {1}", 
                    new Object[]{iframeLocator, e.getMessage()});
            throw e;
        }
    }
 
    public void switchToParentFrame() {
        try {
            driver.switchTo().parentFrame();
            logger.log(Level.INFO, "Switched to parent frame from iframe: {0}", iframeLocator);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to switch to parent frame. Exception: {0}", e.getMessage());
            throw e;
        }
    }
 
    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
            logger.log(Level.INFO, "Switched back to default content.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to switch back to default content. Exception: {0}", e.getMessage());
            throw e;
        }
    }
 
    public boolean isIFramePresent() {
        try {
            boolean present = !driver.findElements(iframeLocator).isEmpty();
            logger.log(Level.INFO, "IFrame presence check for {0}: {1}", new Object[]{iframeLocator, present});
            return present;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking iframe presence. Exception: {0}", e.getMessage());
            return false;
        }
    }
}
