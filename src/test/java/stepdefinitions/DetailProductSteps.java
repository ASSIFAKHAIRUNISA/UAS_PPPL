package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.DriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DetailProductSteps {

    private final DetailProductPage detailProductPage = new DetailProductPage();
    private WhatsAppPage whatsAppPage;
    private TransaksiPage transaksiPage;
    private LoginPage loginPage;

    @Given("user is on the product detail page")
    public void  user_is_on_the_detail_product_page() {
        assertTrue(detailProductPage.isPesanButtonVisible(), "Button 'Pesan' tidak terlihat. Halaman detail produk gagal dimuat.");
    }

    @And("user should see the Pesan button")
    public void user_should_see_the_product_detail_page() {
        assertTrue(detailProductPage.isPesanButtonVisible(), "Button 'Pesan' tidak terlihat. Halaman detail produk gagal dimuat.");
    }

    @And("user clicks the Pesan button")
    public void user_clicks_the_pesan_button() {
        detailProductPage.clickPesanButton();
    }

    @Then("user should be redirected to the WhatApp page")
    public void user_should_be_redirected_to_the_whatsapp_page(){
        whatsAppPage = new WhatsAppPage();
        assertTrue(whatsAppPage.isContinueToWhatsAppButtonVisible());
    }

    @Then("user click the Foto Profile")
    public void user_click_the_foto_profile(){
        detailProductPage.clickPhotoProfile();
    }

    @And("user click the Profile button")
    public void user_click_the_profile_button(){
        detailProductPage.clickProfileButton();
    }

    @Then("user should be redirected to the tansaksi page")
    public void user_should_be_redirected_to_the_tracsakasi_page(){
        transaksiPage = new TransaksiPage();
        transaksiPage.isOnTheTransaksiPage();
    }

    @Given("Foto Profile is visible")
    public void foto_profile_is_visible() {
        detailProductPage.isPhotoProfileVisible();
    }


    @And("user click the Sign Out button")
    public void user_click_the_sign_out_button() {
        detailProductPage.clickSignOutButton();
    }

    @Then("user should be redirected to the login page")
    public void user_should_be_redirected_to_the_login_page() {
        loginPage = new LoginPage();
        assertTrue(loginPage.isOnTheLoginPage());

    }
}
