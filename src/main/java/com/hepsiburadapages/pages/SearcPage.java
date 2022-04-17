package com.hepsiburadapages.pages;

import com.hepsiburadabase.commons.CommonsBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearcPage extends BasePage {

	public SearcPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public SearcPage(CommonsBasePage lib) {
		super.lib = lib;
		PageFactory.initElements(lib.driver, this);
	}


	public ProductPage uruneGit(Integer siraNo) {

		lib.click(By.xpath("//ul[@class='productListContent-wrapper productListContent-grid-0']/li[" + siraNo + "]"));

		return new ProductPage(lib);
	}




}
