package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SideMenuPage extends PageBase {
    public SideMenuPage(WebDriver driver) {
        super(driver);
    }

    By sideMenu = By.xpath("//*[@id=\"nav-hamburger-menu\"]");
    By seeAll = By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[14]");
    By allVideoGames = By.xpath("//*[@id=\"hmenu-content\"]/ul[32]/li[3]");
    By videoGames = By.xpath("//a[@data-ref-tag='nav_em_1_1_1_21']");

    @Step("Click On Side Menu")
    public SideMenuPage clickOnSideMenu() {
        clickElement(sideMenu);
        return this;
    }

    @Step("Click On See All Link")
    public SideMenuPage clickOnSeeAll() {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(seeAll);
        action.moveToElement(element).click().perform();
        return this;
    }

    @Step("Click On Video Games From Side Menu")
    public SideMenuPage clickOnVideoGames(String text) {
        scrollByVisibleText(text);
        clickElement(videoGames);
        return this;
    }

    @Step("Click On All Video Games From Side Menu")
    public ListingPage clickOnAllVideoGames() {
        clickElement(allVideoGames);
        return new ListingPage(driver);
    }
}
