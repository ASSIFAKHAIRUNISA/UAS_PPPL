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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Ubah timeout di sini
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

}
