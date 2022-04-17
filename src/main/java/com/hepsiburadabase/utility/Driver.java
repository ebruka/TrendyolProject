package com.hepsiburadabase.utility;

import java.time.Duration;

import com.hepsiburadabase.base.DriverOptions;
import com.hepsiburadabase.data.GetData;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    private Driver() {
    }

    // Sabit bir değişken olduğu için 'final' yapıldı
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void setUp(String browser) {
        DriverOptions driverOptions = new DriverOptions();

        // this line will tell which browser should open based on the value from
        // properties file
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = driverOptions.chromeUp();
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefox = driverOptions.firefoxUp();
                driver.set(new FirefoxDriver(firefox));
                break;


            default:
                if (browser.equals("iPhone X") || browser.equals("iPhone 6/7/8") || browser.equals("iPad")) {
                    WebDriverManager.chromedriver().setup();
                   // WebDriverManager.chromedriver().driverVersion(version).setup();
                    ChromeOptions mobileOptions = driverOptions.mobileUp(browser);
                    driver.set(new ChromeDriver(mobileOptions));
                }
        }


        driver.get()
                .manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));
        driver.get()
                .manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(GetData.DEFAULT_WAIT_LOADERBOX));



    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        if (getDriver() != null) {
            //getDriver().close(); //firefox'ta hata veriyor. Quit metodu tek başına yeterli.
            getDriver().quit();
            driver.remove();
        }
    }

    public static void takeScreenShot() {
        takeScreenShot("");
    }

    @Attachment(value = "ScreenShot : {0}" , type = "image/png")
    public static byte[] takeScreenShot(String message) {
        if(Driver.getDriver() == null) {
            Log.fail("Driver null olduğu için ekran görüntüsü alınamadı.");
        }
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }


}
