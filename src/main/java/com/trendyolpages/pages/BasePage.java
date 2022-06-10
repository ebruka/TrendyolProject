package com.trendyolpages.pages;

import com.trendyolbase.commons.CommonsBasePage;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
     CommonsBasePage lib = new CommonsBasePage();

    public BasePage() {
        PageFactory.initElements(lib.driver, this);
    }
}
