package com.refindspppl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object untuk halaman Login.
 * Berisi method untuk interaksi dengan elemen di halaman Login.
 */
public class LoginPage {
    private WebDriver driver;

    // Selector elemen
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@class='my-4 bg-mainblue text-white py-2 px-4 rounded-xl w-1/6' and text()='Login']");
    private By errorMessage = By.className("loginError");

    /**
     * Konstruktor LoginPage
     * @param driver WebDriver yang digunakan
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Melakukan login
     */
    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    /**
     * Mendapatkan pesan error
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
