package com.amazon.tests;

import com.amazon.utils.DriverFactory;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class TestBase {
    protected WebDriver driver;
    public SoftAssert softAssertion;

    @Step("Open The Driver With The Targeted Browser 'Chrome'")
    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        softAssertion = new SoftAssert();
        driver = new DriverFactory().initializeDriver(browser);
        driver.get("https://www.amazon.eg/?language=en");
    }

    @Step("Close The Driver")
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Step("Save The Screenshot")
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Verify that Expected Result is equal to Actual Result")
    public void doAssertEqual(Object Actual, Object Expected) {
        softAssertion.assertEquals(Actual, Expected);
        if (Actual != Expected) // Screenshot is taken in case of Failure
        {
            saveScreenshotPNG();
        }
    }
}
