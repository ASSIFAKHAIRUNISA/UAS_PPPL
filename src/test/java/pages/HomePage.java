package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By product = By.xpath("/html/body/main/div/div[3]/div/a[1]/div/img");
    private By logo = By.xpath("/html/body/header/div/div[1]/a[2]/img");

    public HomePage() {super();};

    public void clickProduct(){
        waitClickable(product).click();
    }
    public boolean isLogoVisible() {
        return waitVisible(logo).isDisplayed();
    }
}

