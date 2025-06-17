package com.refindspppl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object untuk halaman Register.
 * Berisi method untuk interaksi dengan elemen di halaman Register.
 */
public class RegisterPage {
    private WebDriver driver;

    // Selector elemen
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By registerButton = By.id("registerSubmit");
    private By errorMessage = By.id("registerError");

    /**
     * Konstruktor RegisterPage
     * @param driver WebDriver yang digunakan
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Mengisi form register
     */
    public void register(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    /**
     * Mendapatkan pesan error
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
