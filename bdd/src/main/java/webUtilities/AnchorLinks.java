package webUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnchorLinks extends Elements {

    public AnchorLinks(By by, WebDriver driver) {
        super(by, driver);
    }

    public void clickLink() {
        getElement().click();
    }

    public String clickLinkAndGetNavigateUrl(){
        clickLink();
        return getCurrentAppUrl();
    }
}
