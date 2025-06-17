package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.DetailProductPage;
import pages.HomePage;
import pages.WhatsAppPage;
import utils.DriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WhatsAppPageSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final WhatsAppPage whatsAppPage = new WhatsAppPage();
    private DetailProductPage detailProductPage;

    @Given("user is on the WhatsApp page")
    public  void user_is_on_the_whatsapp_page(){
        assertTrue(whatsAppPage.isOnTheWahtsAppPage());
    }

    @And("continue to WhatsApp button is visible")
    public void continue_to_whatsapp_button_visible(){
        assertTrue(whatsAppPage.isContinueToWhatsAppButtonVisible());
    }

    @And("user clicks the browser back button")
    public void user_clicks_the_browser_back_button() {
        driver.navigate().back();
    }

    @Then("user should be redirected to detail product page")
    public void user_should_be_redirected_to_detail_product_page() {
        detailProductPage = new DetailProductPage();
        assertTrue(detailProductPage.isOnTheDetailProductPage());
    }


}
