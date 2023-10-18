package com.amazon.tests;

import com.amazon.pages.*;
import org.testng.annotations.Test;

public class AmazonShoppingTest extends TestBase {

    @Test(priority = 1, description = "Given I am on Amazon Home Page, When The User Clicked On Sign In Button Back Then Sign In Page Is Opened")
    public void checkSignInPageIsOpened() {
        boolean verifySignInPageIsOpened = new HomePage(driver)
                .clickOnSignInButton()
                .checkSignInPageIsOpened();
        doAssertEqual(verifySignInPageIsOpened, true);
        softAssertion.assertAll();
    }

    @Test(priority = 2, description = "Given I am on Sign In Page, When The User Logged In Then User Name Appears")
    public void loginTest() {
        new SignInPage(driver)
                .signIn("netiy12045@klanze.com", "123456");
        boolean checkUserLoggedInSuccessfully = new HomePage(driver)
                .checkUserLoggedInSuccessfully("Dummy");
        doAssertEqual(checkUserLoggedInSuccessfully, true);
        softAssertion.assertAll();
    }

    @Test(priority = 3, description = "Given The User Clicks on Side Menu, When Clicks on Video Games Then Video Games Page Is Opened Successfully")
    public void checkVideoGamesIsOpened() {
        boolean checkVideoGamesPageIsOpened = new SideMenuPage(driver)
                .clickOnSideMenu()
                .clickOnSeeAll()
                .clickOnVideoGames("Video Games")
                .clickOnAllVideoGames()
                .checkVideoGamesIsOpened("Video Games");
        doAssertEqual(checkVideoGamesPageIsOpened, true);
        softAssertion.assertAll();
    }

    @Test(priority = 4, description = "The User Can Filter By Free Shipping and New Condition , The Make Sure The Search Result Is opened")
    public void checkSearchResultPageIsOpened() {
        boolean checkResultPageIsOpened = new ListingPage(driver)
                .clickOnFreeShipping()
                .clickNewCondition("Deals & Discounts")
                .checkSearchResultIsOpened("Results");
        doAssertEqual(checkResultPageIsOpened, true);
        softAssertion.assertAll();
    }

    @Test(priority = 5, description = "The User Can Add All Products Less Than 15000 To The Cart")
    public void checkAllProductsAlreadyAddedToCart() {
        boolean checkAllProductsAlreadyAddedToCart = new ListingDetailsPage(driver)
                .addProductsToCart()
                .checkAllProductsAlreadyAddedToCart();
        doAssertEqual(checkAllProductsAlreadyAddedToCart, true);
        softAssertion.assertAll();
    }

    @Test(priority = 6, description = "Given I am on Listing Details Page, When The User Clicks on Proceed to Buy Then Verify That Checkout Page Is Opened")
    public void checkCheckoutPageIsOpened() {
        boolean checkCheckoutIsOpened = new CartPage(driver)
                .proceedToBuy()
                .verifyCheckoutPageIsOpened("Checkout");
        doAssertEqual(checkCheckoutIsOpened, true);
        softAssertion.assertAll();
    }

    @Test(priority = 7, description = "Given I am on Checkout Page, When The User Select The Payment Method Then Verify That The Payment Button Is Enabled")
    public void checkThePaymentMethodButtonIsEnabled() {
        boolean checkPaymentButtonIsEnabled = new CheckoutPage(driver)
                .selectThePaymentMethod()
                .checkPaymentButtonIsEnabled();
        doAssertEqual(checkPaymentButtonIsEnabled, true);
        softAssertion.assertAll();
    }

    @Test(priority = 8, description = "Given I am on Checkout Page, Verify That The Total Amount Is Calculated Correctly For All The Products Which Added To The Cart")
    public void checkTotalAmountAppearsCorrectly() {
        boolean checkTotalAmountAppearsCorrectly = new CheckoutPage(driver)
                .checkTotalAmountAppearsCorrectly();
        doAssertEqual(checkTotalAmountAppearsCorrectly, true);
        softAssertion.assertAll();
    }

    @Test(priority = 9, description = "Delete All Product From The Cart To Be More Dynamic")
    public void deleteAllProductFromTheCart() {
        boolean checkCartIsEmptyAfterDeletingTheItems = new CheckoutPage(driver)
                .clickOnProductsCountsInCheckOutPage()
                .clickOnReturnToCartButton()
                .deleteAllProductsFromCart()
                .checkCartIsEmptyAfterDeletingTheItems("Your Amazon Cart is empty.");
        doAssertEqual(checkCartIsEmptyAfterDeletingTheItems, true);
        softAssertion.assertAll();
    }

}
