package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    By userNameText = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");
    By signInHoverLink = By.xpath("//span[@id='nav-link-accountList-nav-line-1']");

    @Step("Click On Sign In Button")
    public SignInPage clickOnSignInButton() {
        waitForPageToLoad();
        clickElement(signInHoverLink);
        return new SignInPage(driver);
    }

    @Step("Verify The User Is Logged In Successfully")
    public boolean checkUserLoggedInSuccessfully(String text) {
        return getElementText(userNameText).contains(text);
    }
}