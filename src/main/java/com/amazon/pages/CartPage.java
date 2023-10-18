package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends PageBase {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    By proceedToBuyButton = By.xpath("//input[@name='proceedToRetailCheckout']");
    By deleteButtonsList = By.xpath("//*[@id='activeCartViewForm']//*[@value='Delete']");
    By cartHeader = By.xpath("//h1[@class='a-spacing-mini a-spacing-top-base']");

    @Step("Click on 'Proceed To Buy' button")
    public CheckoutPage proceedToBuy() {
        clickElement(proceedToBuyButton);
        waitForPageToLoad();
        return new CheckoutPage(driver);
    }

    @Step("Get all 'Delete' buttons in Cart Page")
    private List<WebElement> getAllDeleteButtons() {
        return driver.findElements(deleteButtonsList);
    }

    @Step("Delete all products from the cart")
    public CartPage deleteAllProductsFromCart() {
        List<WebElement> deleteButtons = getAllDeleteButtons();
        int numberOfItems = deleteButtons.size(); // Get the Count of Delete Buttons
        for (int i = numberOfItems - 1; i >= 0; i--) {
            WebElement deleteButton = deleteButtons.get(i);
            deleteButton.click();
            waitForPageToLoad();
            scrollToTop();
            deleteButtons = getAllDeleteButtons(); // Update the list of delete buttons
        }
        return this;
    }

    @Step("Check if the cart is empty after deleting items")
    public Boolean checkCartIsEmptyAfterDeletingTheItems(String text) {
        return getElementText(cartHeader).contains(text);
    }

}
