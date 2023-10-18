package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListingPage extends PageBase {
    public ListingPage(WebDriver driver) {
        super(driver);
    }

    By videoGamesTitleText = By.xpath("//b[normalize-space()='Video Games']");
    By freeShippingCheckBox = By.xpath("//label[@for='apb-browse-refinements-checkbox_2']//i[@class='a-icon a-icon-checkbox']");

    @Step("Check Video Games Page Is Opened")
    public boolean checkVideoGamesIsOpened(String text) {
        waitForPageToLoad();
        return getElementText(videoGamesTitleText).contains(text);
    }

    @Step("Click On Free Shipping CheckBox")
    public SearchResultPage clickOnFreeShipping() {
        clickElement(freeShippingCheckBox);
        return new SearchResultPage(driver);
    }


}
