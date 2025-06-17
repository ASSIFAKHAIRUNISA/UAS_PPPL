package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailProductPage extends BasePage {
    private By pesanButton = By.xpath("/html/body/main/div/div/div[2]/button");
    private By productName = By.xpath("/html/body/main/div/div/div[2]/p[1]");
    private By photoProfile = By.xpath("/html/body/header/div/div[4]/div/button/img");
    private By profileButton = By.xpath("/html/body/header/div/div[4]/div/div/ul/li[1]/a/p");
    private By signOutButton = By.xpath("/html/body/header/div/div[4]/div/div/ul/li[3]/button/p");

    public static final String EXPECTED_URL = "detail_produk";



    public DetailProductPage() {super();};

    public boolean isOnTheDetailProductPage() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println("DEBUG: Current URL is " + currentUrl);
        return currentUrl.contains(EXPECTED_URL);
    }

    public void clickPesanButton(){
        waitClickable(pesanButton).click();
    }

    public boolean isPesanButtonVisible() {
        return waitVisible(pesanButton).isDisplayed();
    }

    public boolean isPhotoProfileVisible(){
        return waitVisible(photoProfile).isDisplayed();
    }

    public void clickProfileButton(){
        waitClickable(profileButton).click();
    }

    public void clickPhotoProfile(){
        waitClickable(photoProfile).click();
    }

    public void clickSignOutButton(){
        waitClickable(signOutButton).click();
    }


}
