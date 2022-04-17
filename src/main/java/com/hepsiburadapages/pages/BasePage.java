package com.hepsiburadapages.pages;

import com.hepsiburadabase.commons.CommonsBasePage;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
     CommonsBasePage lib = new CommonsBasePage();

    public BasePage() {
        PageFactory.initElements(lib.driver, this);
    }
}
