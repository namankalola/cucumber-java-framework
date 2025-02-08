package webUtilities;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShadowDomElement {

    private static final Logger logger = Logger.getLogger(ShadowDomElement.class.getName());
    private final WebDriver driver;
    private final By shadowHost;

    public ShadowDomElement(By shadowHost, WebDriver driver) {
        this.shadowHost = shadowHost;
        this.driver = driver;
    }

    private WebElement getShadowRoot() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement hostElement = driver.findElement(shadowHost);
        WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", hostElement);
        logger.log(Level.INFO, "Retrieved shadow root for host: {0}", shadowHost);
        return shadowRoot;
    }

    public WebElement findElementInsideShadowDom(By elementInsideShadowDom) {
        WebElement shadowRoot = getShadowRoot();
        WebElement element = shadowRoot.findElement(elementInsideShadowDom);
        logger.log(Level.INFO, "Found element inside Shadow DOM: {0}", elementInsideShadowDom);
        return element;
    }

    public void clickElementInsideShadowDom(By elementInsideShadowDom) {
        WebElement element = findElementInsideShadowDom(elementInsideShadowDom);
        element.click();
        logger.log(Level.INFO, "Clicked on element inside Shadow DOM: {0}", elementInsideShadowDom);
    }

    public String getTextFromShadowDomElement(By elementInsideShadowDom) {
        WebElement element = findElementInsideShadowDom(elementInsideShadowDom);
        String text = element.getText();
        logger.log(Level.INFO, "Text retrieved from Shadow DOM element {0}: {1}",
                new Object[]{elementInsideShadowDom, text});
        return text;
    }

    public void sendKeysToShadowDomElement(By elementInsideShadowDom, String text) {
        WebElement element = findElementInsideShadowDom(elementInsideShadowDom);
        element.sendKeys(text);
        logger.log(Level.INFO, "Entered text ''{0}'' into Shadow DOM element: {1}",
                new Object[]{text, elementInsideShadowDom});
    }
}