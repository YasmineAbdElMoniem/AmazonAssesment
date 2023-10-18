package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.amazon.pages.ListingDetailsPage.numericText;

public class CheckoutPage extends PageBase {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    By valuPaymentMethodRadio = By.xpath("//input[@value='instrumentId=amzn1.pm.pma.gil-TG9hbjpHbG9iYWxJbnN0YWxsbWVudExlbmRpbmdDcmVkaXRMaW5l.QTNNWTZDQUNORElZUUE&isExpired=false&paymentMethod=Loan&tfxEligible=false']");
    By checkoutTitle = By.xpath("//div[@class='a-column a-span8']/h1");
    By paymentMethodButton = By.xpath("//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']");
    By totalAmount = By.xpath("//*[@id='subtotals-marketplace-table']//tr[1]/td[2]");
    By productsCountLink = By.xpath("//span[@class='a-color-link clickable-heading']");
    By returnToCartButton = By.xpath("//a[@href='https://www.amazon.eg/-/en/gp/cart/view.html/ref=chk_cart_link_return_to_cart']");

    @Step("Select the payment method")
    public CheckoutPage selectThePaymentMethod() {
        clickElement(valuPaymentMethodRadio);
        return this;
    }

    @Step("Verify Checkout Page is opened")
    public boolean verifyCheckoutPageIsOpened(String title) {
        return getElementText(checkoutTitle).contains(title);
    }

    @Step("Check if Payment button is enabled")
    public boolean checkPaymentButtonIsEnabled() {
        return checkElementIsEnabled(paymentMethodButton);
    }

    @Step("Check if Total Amount appears correctly")
    public boolean checkTotalAmountAppearsCorrectly() {
        int finalPrice = numericText;     // Get the Total Price which is calculated in Listing Details and Put it in a variable
        String productAmountText = getElementText(totalAmount);     // Get the text of the itemAmount element
        int productsTotalPrice = Integer.parseInt(productAmountText.split("\\.")[0].replaceAll("\\D", ""));  // Make the Final price as an integer without decimals number
        return productsTotalPrice == finalPrice;       // Compare with 2 Values
    }

    @Step("Click on Products Count in Checkout Page")
    public CheckoutPage clickOnProductsCountsInCheckOutPage() {
        clickElement(productsCountLink);
        return this;
    }

    @Step("Click on 'Return to Cart' button")
    public CartPage clickOnReturnToCartButton() {
        clickElement(returnToCartButton);
        waitForPageToLoad();
        return new CartPage(driver);
    }
}
