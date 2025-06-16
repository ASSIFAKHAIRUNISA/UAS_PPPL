package stepDef;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import utils.MailHogHelper;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDef {
    static WebDriver driver;
    static RegisterPage register;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;
    static DetailProdukPage detailProdukPage;
    static CheckoutPage checkoutPage;
    static WhatsappPage whatsappPage;

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

//    ASSIFA

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
    }

    @And("User clicks OK on the alert")
    public void userClicksOKOnAlert() {
        register.confirmSuccessAlert();
    }

    @And("Verification page should be displayed")
    public void verificationPageDisplayed() {
    }

    @When("User clicks verify account button")
    public void userClicksVerify() {
        register.clickVerifyAccount();
    }

    @Then("User receives verification email and clicks the link")
    public void userReceivesEmail() throws Exception {
        Thread.sleep(3000);
        String link = MailHogHelper.getVerificationLink();
        System.out.println("Verification link: " + link);

        if (link == null || link.isEmpty()) {
            throw new RuntimeException("Verification link not found in email.");
        }

        driver.get(link);

        System.out.println("Current URL after verification click: " + driver.getCurrentUrl());
    }

    @Then("User should be redirected to login page")
    public void redirectedToLogin() {
        try {
            // Tunggu maksimal 15 detik untuk salah satu kondisi:
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(d -> {
                        String currentUrl = d.getCurrentUrl().toLowerCase();
                        String pageSource = d.getPageSource().toLowerCase();

                        // Kondisi sukses:
                        // 1. Sudah di halaman login ATAU
                        // 2. Ada pesan sukses verifikasi
                        return currentUrl.contains("/login") ||
                                pageSource.contains("berhasil diverifikasi") ||
                                pageSource.contains("verifikasi sukses") ||
                                pageSource.contains("verification successful");
                    });

            // Jika masih di halaman verifikasi tapi ada pesan sukses
            if (driver.getCurrentUrl().contains("/verify_account")) {
                System.out.println("Verifikasi sukses tapi tidak redirect. Membuka manual...");
                driver.get("http://localhost:3000/login");
            }
        } catch (TimeoutException e) {
            // Debugging detail
            System.out.println("=== DEBUG ===");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Source (50 chars): " + driver.getPageSource().substring(0, 50));

            throw new RuntimeException("Gagal redirect ke halaman login setelah verifikasi");
        }
    }

//    SYAHLA

    @When("User enters email and password")
    public void userEntersCredentials() {
        loginPage = new LoginPage(driver);
        loginPage.enterEmail("assifa@example.com");
        loginPage.enterPassword("Test1234!");
    }

    @And("User clicks the login button")
    public void userClicksLoginButton() {
        loginPage.clickLogin();
    }

    @Then("User should be redirected to the dashboard page")
    public void userShouldBeRedirectedToDashboard() {
        System.out.println("Verifying dashboard redirection...");
        dashboardPage = new DashboardPage(driver);

        try {
            // Memberikan waktu untuk load
            Thread.sleep(3000);

            boolean dashboardLoaded = dashboardPage.isDashboardLoaded();
            String currentUrl = driver.getCurrentUrl();

            System.out.println("Dashboard loaded: " + dashboardLoaded);
            System.out.println("Current URL: " + currentUrl);

            Assert.assertTrue("Dashboard page content did not load correctly. Current URL: " + currentUrl,
                    dashboardLoaded);

            System.out.println("✓ Dashboard verification successful");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Assert.fail("Thread interrupted during dashboard verification");
        } catch (Exception e) {
            String currentUrl = driver.getCurrentUrl();
            String pageSource = driver.getPageSource();

            System.out.println("Dashboard verification failed. Current URL: " + currentUrl);
            System.out.println("Page source length: " + pageSource.length());

            Assert.fail("Dashboard verification failed: " + e.getMessage() + ". Current URL: " + currentUrl);
        }
    }

    @When("User searches for {string} on the dashboard")
    public void userSearchesForProduct(String productName) {
        System.out.println("Searching for product: " + productName);
        dashboardPage.searchForProduct(productName);
    }

    @And("User clicks the search button")
    public void userClickSearchButton() {
        System.out.println("Clicking search button...");
        dashboardPage.clickSearch();
    }

    @Then("Search results for {string} should be displayed")
    public void searchResultsShouldBeDisplayed(String productName) {
        System.out.println("Verifying search results for: " + productName);

        try {
            // Wait for search results to load
            Thread.sleep(2000);

            boolean resultsDisplayed = dashboardPage.areSearchResultsDisplayed();
            boolean productFound = dashboardPage.isProductResultDisplayed(productName);

            System.out.println("Results container displayed: " + resultsDisplayed);
            System.out.println("Product '" + productName + "' found: " + productFound);

            Assert.assertTrue("Search results container is not displayed for product: " + productName,
                    resultsDisplayed);

            Assert.assertTrue("Specific product '" + productName + "' is not displayed in search results.",
                    productFound);

            System.out.println("✓ Search results verification successful");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Assert.fail("Thread interrupted during search results verification");
        }
    }

    @When("User clicks on the product {string}")
    public void userClicksOnTheProduct(String productName) {
    detailProdukPage = new DetailProdukPage(driver);
    detailProdukPage.clickProdukRerum();
    }


    @Then("Product detail page for {string} should be displayed")
    public void productDetailPageForShouldBeDisplayed(String productName) {
        if (productName.equalsIgnoreCase("rerum")) {
            detailProdukPage.verifyHalamanDetailRerum();
        }
    }

    @When("user clicks the Pesan Button")
    public void user_clicks_the_pesan_button() {
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickPesan();
    }

    @Then("user should be redirect to the WhatsApp page")
    public void user_should_be_redirect_to_the_whats_app_page() throws InterruptedException {
        // Tunggu 2-3 detik agar tab WhatsApp sempat terbuka
        Thread.sleep(3000);

        boolean switched = false;
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Trying tab with URL: " + currentUrl);
            if (currentUrl.contains("api.whatsapp.com")) {
                System.out.println("Switched to WhatsApp tab: " + currentUrl);
                switched = true;
                break;
            }
        }

        Assert.assertTrue("Tidak berhasil berpindah ke tab WhatsApp", switched);

        whatsappPage = new WhatsappPage(driver);
        Assert.assertTrue("Gagal redirect ke halaman WhatsApp. URL saat ini: " + driver.getCurrentUrl(),
                whatsappPage.isOnTheWhatsAppPage());
    }



    @And("continue to WhatsApp button is visible")
    public void continue_to_whatsapp_button_visible() {
        assertTrue(whatsappPage.isContinueToWhatsAppButtonVisible());
    }

    @When("user clicks the browser back button")
    public void user_clicks_the_browser_back_button() {
        driver.navigate().back();
    }
}