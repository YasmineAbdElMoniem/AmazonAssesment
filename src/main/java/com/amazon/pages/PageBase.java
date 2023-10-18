package com.amazon.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PageBase {
    public PageBase(WebDriver driver) {
        PageBase.driver = driver;
    }

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @Step("Wait For Element To Be Clickable And Visible")
    public void visibilityWaitForElementToBeClickable(By element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(element)));
    }

    @Step("Wait For Element To Be Located")
    protected void visibilityWaitForElementLocated(By element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    @Step("Click On Element")
    protected void clickElement(By element) {
        visibilityWaitForElementToBeClickable(element);
        driver.findElement(element).click();
    }

    @Step("Send The Text")
    protected void sendText(By element, String text) {
        visibilityWaitForElementToBeClickable(element);
        driver.findElement(element).sendKeys(text);
    }

    @Step("Check Element Is Displayed")
    protected boolean checkElementIsDisplayed(By element) {
        try {
            visibilityWaitForElementLocated(element);
            driver.findElement(element).isDisplayed();
        } catch (Exception e) {
            System.out.println("Couldn't find element" + element);
            return false;
        }
        return true;
    }

    @Step("Get Element Text")
    protected String getElementText(By ele) {
        visibilityWaitForElementLocated(ele);
        return driver.findElement(ele).getText();
    }

    @Step("Scroll By Visible Text")
    protected void scrollByVisibleText(String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the page till the element is found
        WebElement element = driver.findElement(By.xpath("//*[ text() = '" + text + "']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Step("Wait For Page To Load")
    protected static void waitForPageToLoad() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Logger logger = LoggerFactory.getLogger(PageBase.class); // Replace YourClassName with the actual class name
        try {
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        } catch (TimeoutException timeoutException) {
            logger.error("Timeout waiting for page to load: {}", timeoutException.getMessage());
        }
    }

    @Step("Scroll Down By Pixels")
    protected void scrollDownByPixels(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    @Step("Check Element Is Enabled")
    public boolean checkElementIsEnabled(By element) {
        visibilityWaitForElementLocated(element);
        return driver.findElement(element).isEnabled();
    }

    @Step("Scroll To The Top Of The Page")
    protected void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }
}