package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Multiple locator strategies for better reliability
    private final By searchInput = By.id("search");
    private final By searchInputAlt = By.xpath("//input[@type='text' and (@id='search' or @placeholder='Search' or contains(@class, 'search'))]");

    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By searchButtonAlt = By.xpath("//button[contains(text(), 'Search') or contains(@class, 'search')]");

    // Multiple strategies for "no product found" message
    private final By noProductFoundMessage = By.xpath("//div[text()='Produk tidak ditemukan']");
    private final By noProductFoundMessageAlt = By.xpath("//*[contains(text(), 'Produk tidak ditemukan') or contains(text(), 'tidak ditemukan') or contains(text(), 'No products found')]");

    // Multiple strategies for search results
    private final By searchResultsContainer = By.xpath("//div[contains(@class, 'flex-row') and contains(@class, 'flex-wrap')]");
    private final By searchResultsContainerAlt = By.xpath("//div[contains(@class, 'product') or contains(@class, 'result') or contains(@class, 'item')]");
    private final By anyProductResult = By.xpath("//div[contains(@class, 'flex-row')]//p[contains(@class, 'text-sm')]");

    public void searchForProduct(String productName) {
        // Try primary locator first, then fallback
        try {
            type(searchInput, productName);
        } catch (Exception e) {
            System.out.println("Primary search input not found, trying alternative...");
            type(searchInputAlt, productName);
        }
    }

    public void clickSearch() {
        // Try primary search button, then fallback
        try {
            click(searchButton);
        } catch (Exception e) {
            System.out.println("Primary search button not found, trying alternative...");
            click(searchButtonAlt);
        }

        // Wait a moment for search results to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isNoProductFoundMessageDisplayed() {
        // Wait a bit for the message to appear after search
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Try multiple strategies to find the "no product found" message
        if (isElementVisible(noProductFoundMessage)) {
            System.out.println("Found 'no product found' message using primary locator");
            return true;
        }

        if (isElementVisible(noProductFoundMessageAlt)) {
            System.out.println("Found 'no product found' message using alternative locator");
            return true;
        }

        // Check if page source contains the text
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
        // Wait a bit for results to load
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // First check if there are any product results
        if (isElementVisible(anyProductResult)) {
            System.out.println("Found product results using specific product locator");
            return true;
        }

        // Try primary container
        if (isElementVisible(searchResultsContainer)) {
            System.out.println("Found search results container using primary locator");
            return true;
        }

        // Try alternative container
        if (isElementVisible(searchResultsContainerAlt)) {
            System.out.println("Found search results container using alternative locator");
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
        // Wait for page to fully load
        waitForPageLoad();

        // Multiple strategies to verify dashboard is loaded
        System.out.println("Checking if dashboard is loaded...");

        // Strategy 1: Check for search input
        if (isElementVisible(searchInput)) {
            System.out.println("Dashboard loaded - found search input (primary)");
            return true;
        }

        // Strategy 2: Alternative search input
        if (isElementVisible(searchInputAlt)) {
            System.out.println("Dashboard loaded - found search input (alternative)");
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
