package com.refindspppl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object untuk halaman Checkout.
 * Berisi method untuk interaksi dengan elemen di halaman Checkout.
 */
public class CheckoutPage {
    private WebDriver driver;

    // Selector elemen
    private By addressField = By.id("address");
    private By checkoutButton = By.id("checkoutBtn");
    private By errorMessage = By.id("checkoutError");

    /**
     * Konstruktor CheckoutPage
     * @param driver WebDriver yang digunakan
     */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Melakukan checkout
     */
    public void checkout(String address) {
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(checkoutButton).click();
    }

    /**
     * Mendapatkan pesan error
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
