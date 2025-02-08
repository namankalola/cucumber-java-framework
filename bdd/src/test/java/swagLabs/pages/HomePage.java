package swagLabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import webUtilities.AnchorLinks;
import webUtilities.Button;
import webUtilities.Elements;

public class HomePage {

    private final WebDriver driver;
    private final By productTitle = By.xpath("//span[@class='title' and text()='Products']");
    private final By humburger = By.xpath("//button[@id='react-burger-menu-btn']");
    private final By logout = By.xpath("//a[@id='logout_sidebar_link']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void productHomePageDisplayed() {
        Assert.assertTrue(new Elements(productTitle, driver).isDisplayed());
    }

    public void clickHumburger() {
        new Button(humburger, driver).click();
    }

    public void clickLogout() {
        new AnchorLinks(logout, driver).click();
    }

}
