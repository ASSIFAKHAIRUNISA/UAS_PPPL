package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {
    private By pesanButton = By.xpath("//button[contains(text(),'Pesan') and contains(@class,'bg-[#0D96C4]')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickPesan() {
        try {
            scrollToElement(pesanButton);
            click(pesanButton);
            System.out.println("Tombol Pesan diklik di halaman Checkout");
        } catch (Exception e) {
            System.out.println("Gagal klik tombol Pesan di halaman Checkout");
            throw e;
        }
    }
}
