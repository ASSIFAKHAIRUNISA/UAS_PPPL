package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WhatsappPage extends BasePage {
    public WhatsappPage(WebDriver driver) {
        super(driver);
    }

    public static final String EXPECTED_URL = "api.whatsapp.com";
    private By continueToWhatsAppButton = By.xpath("//*[@id='action-button']/span");


    public boolean isOnTheWhatsAppPage() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG: Current URL on WhatsApp page: " + currentUrl);
        return currentUrl.contains(EXPECTED_URL);
    }

    public boolean isContinueToWhatsAppButtonVisible() {
        return waitVisible(continueToWhatsAppButton).isDisplayed();
    }

    public void clickContinueToWhatsAppButton() {
        waitClickable(continueToWhatsAppButton).click();
    }
}