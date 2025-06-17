package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;

public class WhatsAppPage extends BasePage{
    private final WebDriver driver = DriverManager.getDriver();
    public static final String EXPECTED_URL = "api.whatsapp.com";
    private By continueToWhatsAppButton = By.xpath("//*[@id=\"action-button\"]/span");

    /**
     * Memeriksa apakah URL saat ini mengandung domain WhatsApp.
     * Cocok digunakan untuk verifikasi redirection setelah klik tombol "Pesan".
     */
    public boolean isOnTheWahtsAppPage() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG: Current URL is " + currentUrl);
        return currentUrl.contains(EXPECTED_URL);
    }

    public boolean isContinueToWhatsAppButtonVisible(){
        return waitVisible(continueToWhatsAppButton).isDisplayed();
    }

    public void clickContinueToWhatsAppButton(){
        waitClickable(continueToWhatsAppButton).click();
    }
}
