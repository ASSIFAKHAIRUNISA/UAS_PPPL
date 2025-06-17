package com.refindspppl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object untuk halaman detail produk.
 * Berisi method untuk interaksi dengan tombol Pesan (WhatsApp).
 */
public class DetailProdukPage {
    private WebDriver driver;
    private WebDriverWait wait;
    // Selector tombol Pesan sesuai HTML terbaru
    private By pesanButton = By.xpath("//button[contains(text(),'Pesan') and contains(@class,'bg-[#0D96C4]')]");

    public DetailProdukPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Klik tombol Pesan (menuju WhatsApp)
     */
    public void clickPesan() {
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(pesanButton));
        driver.findElement(pesanButton).click();
    }
}
