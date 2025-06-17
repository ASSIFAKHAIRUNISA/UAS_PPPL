package com.refindspppl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object untuk halaman Home.
 * Berisi method untuk interaksi dengan elemen di halaman Home.
 */
public class HomePage {
    private WebDriver driver;

    // Selector elemen
    private By registerButton = By.id("registerBtn");
    // Lebih aman: selector berdasarkan text saja
    private By loginButton = By.xpath("//a[normalize-space(text())='Login']");

    /**
     * Konstruktor HomePage
     * @param driver WebDriver yang digunakan
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Klik tombol Register
     */
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    /**
     * Klik tombol Login
     */
    public void clickLogin() {
        System.out.println("Klik tombol Login...");
        driver.findElement(loginButton).click();
        System.out.println("Berhasil klik tombol Login");
    }
}
