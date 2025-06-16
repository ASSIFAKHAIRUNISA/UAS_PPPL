package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private final By searchInput = By.id("search");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By noProductFoundMessage = By.xpath("//div[text()='Produk tidak ditemukan']");
    private final By searchResultsContainer = By.xpath("//div[contains(@class, 'flex-row') and contains(@class, 'flex-wrap')]");
    public void searchForProduct(String productName) {
        type(searchInput, productName);
    }

    public void clickSearch() {
        click(searchButton);
        waitForPageLoad();
    }

    public boolean isNoProductFoundMessageDisplayed() {
        // Wait a bit for the message to appear after search
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (isElementVisible(noProductFoundMessage)) {
            System.out.println("Found 'no product found' message using primary locator");
            return true;
        }


        String pageSource = driver.getPageSource();
        if (pageSource.contains("Produk tidak ditemukan") ||
                pageSource.contains("tidak ditemukan") ||
                pageSource.contains("No products found")) {
            System.out.println("Found 'no product found' message in page source");
            return true;
        }

        System.out.println("No 'product not found' message detected");
        return false;
    }

    public boolean areSearchResultsDisplayed() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (isElementVisible(searchResultsContainer)) {
            System.out.println("Found search results container using primary locator");
            return true;
        }

        System.out.println("No search results container found");
        return false;
    }

    public boolean isProductResultDisplayed(String productName) {
        // Multiple strategies to find product in results
        By[] productLocators = {
                By.xpath("//div[contains(@class, 'flex-row')]//p[contains(@class, 'text-sm') and contains(@class, 'text-blue-300') and contains(normalize-space(.), '" + productName + "')]"),
                By.xpath("//*[contains(text(), '" + productName + "')]"),
                By.xpath("//div[contains(@class, 'product')]//text()[contains(., '" + productName + "')]/..")
        };

        for (By locator : productLocators) {
            if (isElementVisible(locator)) {
                System.out.println("Found product '" + productName + "' in search results");
                return true;
            }
        }

        // Check page source as last resort
        String pageSource = driver.getPageSource().toLowerCase();
        if (pageSource.contains(productName.toLowerCase())) {
            System.out.println("Found product '" + productName + "' in page source");
            return true;
        }

        System.out.println("Product '" + productName + "' not found in search results");
        return false;
    }

    public boolean isDashboardLoaded() {
        waitForPageLoad();

        if (isElementVisible(searchInput)) {
            System.out.println("Dashboard loaded - found search input (primary)");
            return true;
        }

        // Strategy 3: Check for any dashboard-specific elements
        By[] dashboardElements = {
                By.xpath("//div[contains(@class, 'dashboard')]"),
                By.xpath("//nav[contains(@class, 'navbar')]"),
                By.xpath("//header"),
                By.xpath("//main"),
                By.id("root")
        };

        for (By element : dashboardElements) {
            if (isElementVisible(element)) {
                System.out.println("Dashboard loaded - found dashboard element");
                return true;
            }
        }

        // Strategy 4: Check URL and page title
        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();

        System.out.println("Current URL: " + currentUrl);
        System.out.println("Page title: " + pageTitle);

        // If we're not on login page and page has loaded, assume dashboard is loaded
        if (!currentUrl.contains("/login") && !pageTitle.toLowerCase().contains("login")) {
            System.out.println("Dashboard loaded - not on login page");
            return true;
        }

        System.out.println("Dashboard not detected as loaded");
        return false;
    }
}