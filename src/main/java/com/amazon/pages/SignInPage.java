package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends PageBase {

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    By signInText = By.xpath("//h1[normalize-space()='Sign in']");
    By emailTextBox = By.xpath("//input[@id='ap_email']");
    By continueButton = By.xpath("//input[@id='continue']");
    By passwordTextBox = By.xpath("//input[@id='ap_password']");
    By signInButton = By.xpath("//input[@id='signInSubmit']");


    public boolean checkSignInPageIsOpened() {
        waitForPageToLoad();
        return checkElementIsDisplayed(signInText);
    }

    @Step("Enter The Email")
    public SignInPage enterEmail(String email) {
        sendText(emailTextBox, email);
        return this;
    }

    @Step("Enter The Password")
    public SignInPage enterPassword(String email) {
        sendText(passwordTextBox, email);
        return this;
    }

    @Step("Click On Sign In Button")
    public void clickOnSignInBtn() {
        clickElement(signInButton);
    }

    @Step("Click On Continue Button")
    public SignInPage clickOnContinueButton() {
        clickElement(continueButton);
        return this;
    }

    @Step("Login With Valid Email And Password")
    public void signIn(String email, String password) {
        enterEmail(email)
                .clickOnContinueButton()
                .enterPassword(password)
                .clickOnSignInBtn();
    }
}
