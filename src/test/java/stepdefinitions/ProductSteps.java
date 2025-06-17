package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.DetailProductPage;
import pages.HomePage;
import utils.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class ProductSteps {

    private final HomePage homePage = new HomePage();
    private DetailProductPage detailProductPage;

    @Given("user is on the homepage")
    public void user_is_on_the_homepage() {
        assertTrue(homePage.isLogoVisible(), "User tidak berada di homepage.");
    }

    @And("logo is visible")
    public void logo_is_visible() {
        assertTrue(homePage.isLogoVisible(), "Logo tidak terlihat di homepage.");
    }

    @When("user clicks the first product")
    public void user_clicks_the_first_product() {
        homePage.clickProduct();
    }

    @Then("user should see the product detail page")
    public void user_should_see_the_product_detail_page() {
        detailProductPage = new DetailProductPage();
        assertTrue(detailProductPage.isPesanButtonVisible(), "Halaman detail produk tidak tampil dengan benar.");
    }
}
