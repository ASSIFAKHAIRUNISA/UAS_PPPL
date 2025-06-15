package stepDef;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPage;
import utils.MailHogHelper;

import java.time.Duration;

public class RegisterStepDef {
    static WebDriver driver;
    static RegisterPage register;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void teardown() {
        driver.quit();
    }

    @Given("User is on the registration page")
    public void userIsOnRegisterPage() {
        driver.get("http://localhost:3000/register");
        register = new RegisterPage(driver);
    }

    @When("User inputs valid registration data")
    public void userInputsValidRegistrationData() {
        register.fillForm(
                "assifa@example.com",
                "Test1234!",
                "Test1234!",
                "revandratest",
                "Revandra"
        );
    }

    @And("Clicks the register button")
    public void clickRegister() {
        register.clickRegister();
    }

    @Then("A success alert should appear")
    public void successAlertAppears() {
        // wait + validate handled in alert
    }

    @And("User clicks OK on the alert")
    public void userClicksOKOnAlert() {
        register.confirmSuccessAlert();
    }

    @And("Verification page should be displayed")
    public void verificationPageDisplayed() {
        // You may want to wait for some text or element to appear
    }

    @When("User clicks verify account button")
    public void userClicksVerify() {
        register.clickVerifyAccount();
    }

    @Then("User receives verification email and clicks the link")
    public void userReceivesEmail() throws Exception {
        Thread.sleep(3000); // beri waktu agar email benar-benar terkirim
        String link = MailHogHelper.getVerificationLink();
        System.out.println("Verification link: " + link);

        if (link == null || link.isEmpty()) {
            throw new RuntimeException("Verification link not found in email.");
        }

        driver.get(link);  // Akses link
    }


    @Then("User should be redirected to login page")
    public void redirectedToLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tunggu URL berubah ATAU halaman mengandung indikasi berhasil
        boolean success = wait.until(driver ->
                driver.getCurrentUrl().contains("/login") ||
                        driver.getPageSource().toLowerCase().contains("berhasil diverifikasi")
        );

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after verify: " + currentUrl);
        Assert.assertTrue("Verifikasi gagal atau tidak redirect!", success);
    }


}
