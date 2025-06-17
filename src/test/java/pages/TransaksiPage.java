package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TransaksiPage extends BasePage{
    private By pembelianTab = By.xpath("//li[contains(text(), 'Pembelian') and @role='tab']");
    public static final String EXPECTED_URL = "user_transaksi_penjualan";
    public static final String PESANAN_DIBATALKAN_PEMBELI_TEXT = "Pesanan dibatalkan pembeli";
    public By batalkanPesananButton = By.xpath("//*[@id=\"panel:r0:2\"]/div[last()]/div[3]/button[2]\n");
    public By batalkanPesananText = By.xpath("//*[@id=\"panel:R9uuuuj6:2\"]/div[last()]/div[3]/p");
    private By signOutButton = By.xpath("/html/body/header/div/div[4]/div/div/ul/li[3]/button/p");

    public TransaksiPage(){super();};

    public boolean isOnTheTransaksiPage() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG: Current URL is " + currentUrl);
        return currentUrl.contains(EXPECTED_URL);
    }

    public void clickTransaksiTab(){
        waitClickable(pembelianTab).click();
    }

    public void clickBatalkanPesananButton(){
        waitClickable(batalkanPesananButton).click();
    }
    public void clickSignOutButton(){
        waitClickable(signOutButton).click();
    }

    public boolean isPesananSuccessfullyCanceled() {
        WebElement pesanText = waitVisible(batalkanPesananText);
        String text = pesanText.getText().trim();
        return text.equalsIgnoreCase(PESANAN_DIBATALKAN_PEMBELI_TEXT);
    }


}
