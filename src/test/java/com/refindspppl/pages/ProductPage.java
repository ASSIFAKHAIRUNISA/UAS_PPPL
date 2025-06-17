package com.refindspppl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object untuk halaman Product.
 * Berisi method untuk interaksi dengan elemen di halaman Product.
 */
public class ProductPage {
    private WebDriver driver;

    // Selector elemen
    // private By productList = By.id("productList");
    private By addToCartButton = By.id("addToCartBtn");

    /**
     * Konstruktor ProductPage
     * @param driver WebDriver yang digunakan
     */
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Menambah produk ke keranjang
     */
    public void addProductToCart(WebDriver wait) {
        driver.findElement(addToCartButton).click();
    }    /**
     * Klik produk debitis untuk menuju ke detail produk
     */
    public void clickProdukDebit() {
        // Mencari elemen produk debitis dengan berbagai cara
        System.out.println("Mencari produk debitis di halaman...");
        
        try {
            // 1. Coba cari berdasarkan href yang mengarah ke detail_produk/347
            By produkDebitSelector = By.xpath("//a[contains(@href, '/detail_produk/347')]");
            driver.findElement(produkDebitSelector).click();
            System.out.println("Berhasil klik produk melalui href");
            return;
        } catch (Exception e1) {
            System.out.println("Tidak menemukan elemen dengan href, mencoba cara lain...");
            
            try {
                // 2. Coba cari berdasarkan text "debitis" yang dapat diklik
                By produkDebitSelector = By.xpath("//a[contains(text(), 'debitis')] | //*[contains(text(), 'debitis') and (self::a or self::button or self::div[@onclick])]");
                driver.findElement(produkDebitSelector).click();
                System.out.println("Berhasil klik produk melalui text debitis");
                return;
            } catch (Exception e2) {
                System.out.println("Tidak menemukan elemen dengan text debitis, mencoba selector umum...");
                
                try {
                    // 3. Coba cari card produk atau elemen yang mengandung "debitis"
                    By produkDebitSelector = By.xpath("//*[contains(text(), 'debitis')]/ancestor-or-self::*[contains(@class, 'card') or contains(@class, 'product') or contains(@class, 'item')]");
                    driver.findElement(produkDebitSelector).click();
                    System.out.println("Berhasil klik produk melalui card");
                    return;
                } catch (Exception e3) {
                    // 4. Jika semua gagal, print page source untuk debugging
                    System.out.println("Gagal menemukan produk debitis. Page source:");
                    System.out.println(driver.getPageSource());
                    throw new RuntimeException("Tidak dapat menemukan produk debitis di halaman");
                }
            }
        }
    }
}
