package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final LoginPage loginPage = new LoginPage();
    private HomePage homePage;

    @Given("user is on the login page")
    public void user_is_on_the_login_page() {
        driver.get("http://localhost:3000/login"); // Ganti dengan URL login kamu
    }

    @When("user enters email {string} and password {string}")
    public void user_enters_email_and_password(String email, String password) {
        loginPage.enterLoginDetail(email, password);
    }

    @And("user clicks the login button")
    public void user_clicks_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        homePage = new HomePage();
        assertTrue(homePage.isLogoVisible(), "Logo homepage tidak terlihat. Login mungkin gagal.");
    }


}
