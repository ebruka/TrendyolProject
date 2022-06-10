package com.denemebase.controller;

import com.trendyolbase.utility.Driver;
import com.trendyolbase.utility.Terminal;
import com.trendyolbase.utility.TestListeners;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({TestListeners.class})
public class TestController {

    public <T> T startTest(T page){
        ThreadLocal<T> tl = new ThreadLocal<>();
        tl.set(page);
        return tl.get();
    }

    public WebDriver getDriver(String browser) {
        Driver.setUp(browser);

        return Driver.getDriver();
    }

    @Step("Browser closed")
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.closeDriver();
    }

    @BeforeSuite(alwaysRun = true)
    public void removeReportHistory() {
        Terminal.runCommand("allure generate --clean --output allure-results");
    }

    @AfterSuite(alwaysRun = true)
    public void openAllureReport() {
        Terminal.runCommand("allure serve -h localhost");
    }    
}
