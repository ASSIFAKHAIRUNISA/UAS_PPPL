package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitUntilVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilEnabled(By locator) {
        wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed() && element.isEnabled();
        });
    }

    public void click(By locator) {
        waitUntilVisible(locator);
        waitUntilEnabled(locator);
        waitUntilClickable(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitUntilVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void waitForAlertAndAccept() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    // Method to wait for page to load completely
    public void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    // Di BasePage.java
    public void scrollToElement(By locator) {
        WebElement element = waitUntilVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
