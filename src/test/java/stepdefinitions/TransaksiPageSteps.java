package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.TransaksiPage;
import utils.DriverManager;

public class TransaksiPageSteps {
    private final WebDriver driver = DriverManager.getDriver();
    private final TransaksiPage transaksiPage = new TransaksiPage();

    @Given("user is on the tansaksi page")
    public void userIsOnTheTransaksiPage() {
        transaksiPage.isOnTheTransaksiPage();
    }

    @Then("user click the Pembelian tab")
    public void userClickPembelianTab() {
        transaksiPage.clickTransaksiTab();
    }

    @And("user click the Batalkan Pesanan button on the last order")
    public void userClickBatalkanPesananButtonOnTheLastOrder() {
        transaksiPage.clickBatalkanPesananButton();
    }

    @And("user clicks the browser reload button")
    public void userClicksTheBrowserReloadButton() {
        driver.navigate().refresh();
    }

    @Then("the last order should have been canceled")
    public void theLastOrderShouldHaveBeenCanceled() {
        transaksiPage.isOnTheTransaksiPage();
    }
}
