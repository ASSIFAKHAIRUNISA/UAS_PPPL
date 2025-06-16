package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By emailInput = By.id("email");

    private final By passwordInput = By.id("password");

    private final By loginButton = By.xpath("//button[text()='Login']");

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);

    }

    public void clickLogin() {
        click(loginButton);
        waitForPageLoad();
    }
}
