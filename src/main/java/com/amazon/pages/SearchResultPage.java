package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends PageBase {
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    By newCondition = By.xpath("//span[@class='a-size-base a-color-base'][normalize-space()='New']");
    By resultTitleText = By.xpath("//span[@class='a-size-medium-plus a-color-base a-text-bold']");

    @Step("Select New Condition As 'Filter'")
    public SearchResultPage clickNewCondition(String conditionFilter) {
        scrollByVisibleText(conditionFilter);
        clickElement(newCondition);
        return new SearchResultPage(driver);
    }

    @Step("Check Search Result Is Opened After Select 'New' as a condition and Select 'Free Shipping'")
    public boolean checkSearchResultIsOpened(String text) {
        waitForPageToLoad();
        return getElementText(resultTitleText).contains(text);
    }
}
