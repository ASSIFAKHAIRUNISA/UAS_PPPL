package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DetailProdukPage extends BasePage {

    private By productCardRerum = By.xpath("//a[contains(@href,'/detail_produk')]//p[contains(.,'rerum')]/ancestor::a");
    private By pesanButton = By.xpath("//button[contains(text(),'Pesan') and contains(@class,'bg-[#0D96C4]')]");
    private By titleProduk = By.xpath("//*[normalize-space()='rerum']");
    private By tombolPesan = By.xpath("//button[contains(text(),'Pesan')]");

    public DetailProdukPage(WebDriver driver) {
        super(driver);
    }

    public void clickProdukRerum() {
        System.out.println("Mencari card produk 'rerum'...");

        try {
            scrollToElement(productCardRerum);

            click(productCardRerum);
            System.out.println("Berhasil klik card produk 'rerum'");
        } catch (Exception e) {
            System.out.println("Gagal menemukan card produk 'rerum'");
            System.out.println("HTML Context: " + driver.findElement(By.tagName("body")).getText());
            throw new RuntimeException("Failed to click product 'rerum': " + e.getMessage());
        }
    }

    public void verifyHalamanDetailRerum() {
        try {
            // Tunggu hingga judul produk "rerum" muncul
            wait.until(ExpectedConditions.visibilityOfElementLocated(titleProduk));

            // Tunggu hingga tombol "Pesan" bisa diklik
            wait.until(ExpectedConditions.elementToBeClickable(tombolPesan));

            System.out.println("Halaman detail produk 'rerum' berhasil diverifikasi.");
        } catch (Exception e) {
            System.out.println("Gagal verifikasi halaman detail produk");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Source (trimmed): " +
                    driver.getPageSource().substring(0, Math.min(500, driver.getPageSource().length())));
            throw new RuntimeException("Gagal verifikasi halaman detail produk", e);
        }
    }


    public void clickPesan() {
        try {
            scrollToElement(pesanButton);
            click(pesanButton);
            System.out.println("Tombol Pesan diklik");
        } catch (Exception e) {
            System.out.println("Gagal klik tombol Pesan");
            throw e;
        }
    }
}