package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[text()='Login']");
    public static final String EXPECTED_URL = "login";

    public boolean isOnTheLoginPage() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG: Current URL is " + currentUrl);
        return currentUrl.contains(EXPECTED_URL);
    }



    public LoginPage() {
        super(); // tidak perlu driver sebagai parameter
    }

    public void enterLoginDetail(String email, String password) {
        type(emailField, email);
        type(passwordField, password);
    }


    public void clickLoginButton() {
        waitClickable(loginButton).click(); // Tunggu tombol sampai clickable
    }

}
