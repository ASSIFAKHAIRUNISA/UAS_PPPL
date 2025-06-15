package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By emailInput = By.xpath("//input[@placeholder='email']");
    private final By passwordInput = By.xpath("//input[@placeholder='password']");
    private final By confirmPasswordInput = By.xpath("//input[@placeholder='Konfirmasi password']");
    private final By usernameInput = By.xpath("//input[@placeholder='username']");
    private final By nameInput = By.xpath("//input[@placeholder='name']");
    private final By registerButton = By.xpath("//button[text()='Register']");
    private final By verifyButton = By.xpath("//button[contains(text(), 'Kirim Link Verifikasi')]");


    // Actions
    public void fillForm(String email, String password, String confirmPassword, String username, String name) {
        type(emailInput, email);
        type(passwordInput, password);
        type(confirmPasswordInput, confirmPassword);
        type(usernameInput, username);
        type(nameInput, name);
    }

    public void clickRegister() {
        click(registerButton);
    }

    public void confirmSuccessAlert() {
        waitForAlertAndAccept();
    }

    public void clickVerifyAccount() {
        click(verifyButton);
    }
}
