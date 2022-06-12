package com.trendyolbase.base;


import com.trendyolbase.configs.DriverConfig;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DriverOptions {
    private static final String START_FULLSCREEN = "--start-fullscreen";
    private static final String HEADLESS = "--headless";
    private static String driverType;

    public ChromeOptions chromeUp() {

        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);

        ChromeOptions chromeUp = new ChromeOptions();

        chromeUp.setExperimentalOption("prefs", chromePrefs);
        chromeUp.addArguments("--disable-web-security");
        chromeUp.addArguments("--allow-running-insecure-content");
        chromeUp.addArguments("--disable-infobars");
        chromeUp.addArguments("--disable-infobars");
        chromeUp.setExperimentalOption("useAutomationExtension", false);
        chromeUp.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));


        if (DriverConfig.getInstance().isChromeHeadless()) chromeUp.addArguments(HEADLESS,"--window-size=1920x1080");
        else chromeUp.addArguments(START_FULLSCREEN);

        return chromeUp;
    }

    public FirefoxOptions firefoxUp() {
        FirefoxOptions firefoxUp = new FirefoxOptions();
        setDriverType("firefox");

        if (DriverConfig.getInstance().isFirefoxHeadless()) firefoxUp.addArguments(HEADLESS,"--window-size=1920x1080");
        else firefoxUp.addArguments(START_FULLSCREEN);

        return new FirefoxOptions();

    }

    public ChromeOptions mobileUp(String device) {

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", device);
        setDriverType("mobile");

        ChromeOptions mobileOptions = new ChromeOptions();
       // mobileOptions.addArguments("--no-sandbox");
	    mobileOptions.addArguments("--disable-dev-shm-usage");
        //mobileOptions.addArguments("--disable-gpu");
        mobileOptions.addArguments("--no-sandbox");
        mobileOptions.addArguments("--whitelisted-ips");
        mobileOptions.addArguments("--disable-extensions");


        mobileOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        if (DriverConfig.getInstance().isMobileHeadless()) mobileOptions.addArguments(HEADLESS);
        else mobileOptions.addArguments(START_FULLSCREEN);

        return mobileOptions;
    }

    /**
     *
     * @return chrome,firefox,chrome-headless,firefox-headless,mobile tipi döner
     */
    public static String getDriverType() {
        return driverType;
    }

    public static void setDriverType(String driverType) {

        DriverOptions.driverType = driverType;
    }


}
