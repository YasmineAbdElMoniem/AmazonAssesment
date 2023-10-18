package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ListingDetailsPage extends PageBase {
    private int totalPrice = 0;
    private static final int MAX_PRICE = 15000;
    private static final int PIXELS = 250;
    protected static int numericText;

    public ListingDetailsPage(WebDriver driver) {
        super(driver);
    }

    By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");
    By noThanksButton = By.xpath("//*[@id='attachSiNoCoverage']/span/input");
    By productsPriceList = By.xpath("//div[contains(@class, 'a-size-base')]//span[contains(@class, 'a-price-whole')]");
    By cartButton = By.xpath("//*[@id='nav-cart']");
    By proceedToBuyButton = By.xpath("//input[@name='proceedToRetailCheckout']");
    By nextPageButton = By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']");
    By cartCountNumber = By.xpath("//*[@id='nav-cart-count']");

    @Step("Add products to cart")
    private void addToCart() {
        clickElement(addToCartButton);
        if (isNoThanksButtonDisplayed()) {
            clickElement(noThanksButton);
            visibilityWaitForElementLocated(proceedToBuyButton);
        }
    }

    @Step("Check if the 'No Thanks' button is displayed")
    private boolean isNoThanksButtonDisplayed() {
        try {
            return checkElementIsDisplayed(noThanksButton);
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    @Step("Get All Products Price List on The Current Page")
    private List<WebElement> getAllProducts() {
        return driver.findElements(productsPriceList);
    }

    @Step("Check if all products are added to the cart")
    public ListingDetailsPage addProductsToCart() {
        if (!checkProductsPriceOnCurrentPage()) { // Check If There Are Products < 15000 or Not
            while (navigateToNextPage()) { // Navigate to Next Page Till Found Products less Than 15000
                if (checkProductsPriceOnCurrentPage()) {
                    break; // Stop navigating if products less Than 15000 are found on the current page.
                }
            }
        }
        return this;
    }

    @Step("Check products Price on the current page")
    private boolean checkProductsPriceOnCurrentPage() {
        boolean foundProductOver15000 = false;
        for (WebElement product : getAllProducts()) {
            int price = extractPrice(product);
            if (price < MAX_PRICE) {
                totalPrice += price;  // Get The total Price for Every Product I already Clicked on it to Add To The Cart
                openNewTabAndAddToCart(product);
                scrollDownByPixels(PIXELS);
                foundProductOver15000 = true;
            }
        }
        return foundProductOver15000;
    }

    @Step("Get the count of products on the current page with a price less than 15000")
    private int getProductsOnCurrentPageLessThan15000() {
        int productCount = 0;
        for (WebElement product : getAllProducts()) {
            int price = extractPrice(product);
            if (price < MAX_PRICE) {
                productCount++;
            }
        }
        return productCount;   // Get The Count For Every Product I Clicked on it to Add To The Cart
    }

    @Step("Navigate to the next page")
    private boolean navigateToNextPage() {
        WebElement nextButton = driver.findElement(nextPageButton);
        if (nextButton.isDisplayed()) {
            Actions actions = new Actions(driver);
            actions.moveToElement(nextButton)
                    .keyDown(Keys.COMMAND)
                    .click()
                    .keyUp(Keys.COMMAND)
                    .build()
                    .perform();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.numberOfWindowsToBe(2));
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));

            checkProductsPriceOnCurrentPage();

            driver.close(); // Close the old opened tab.
            driver.switchTo().window(tabs.get(1));
            return true;
        }
        return false;
    }

    @Step("Click on 'Cart' button")
    private void clickOnCartButton() {
        clickElement(cartButton);
    }


    public boolean checkAllProductsAlreadyAddedToCart() {
        int productsCount = getProductsOnCurrentPageLessThan15000();
        clickOnCartButton();
        int cartCount = getCartItemCount();
        if (productsCount == cartCount) {      // Check The Count For Every Product I Clicked on it to Add To The Cart Equal to Cart Count
            int finalPrice = getFinalPrice();
            return totalPrice == finalPrice;
        }
        return false;
    }

    @Step("Extract Price Only From The Products List")
    private int extractPrice(WebElement product) {
        String priceText = product.getText();
        return Integer.parseInt(priceText.replaceAll("\\D", ""));
    }

    @Step("Open a new tab and add the product to the cart")
    private void openNewTabAndAddToCart(WebElement product) {
        Actions actions = new Actions(driver);
        actions.moveToElement(product)
                .keyDown(Keys.COMMAND)
                .click()
                .keyUp(Keys.COMMAND)
                .build()
                .perform();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        addToCart();
        driver.close(); // Close The Newly Opened Tab
        driver.switchTo().window(tabs.get(0));
    }

    @Step("Get the count of items in the cart")
    private int getCartItemCount() {
        WebElement cartCountElement = driver.findElement(cartCountNumber);
        return Integer.parseInt(cartCountElement.getText());
    }

    @Step("Get the final price in the cart")
    private static int getFinalPrice() {
        WebElement total = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']"));
        String amount = total.getText();
        numericText = Integer.parseInt(amount.split("\\.")[0].replaceAll("\\D", ""));
        return numericText;
    }

}
