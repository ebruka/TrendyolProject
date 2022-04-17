package com.hepsiburadapages.pages;

import com.hepsiburadabase.commons.CommonsBasePage;
import com.hepsiburadabase.data.GetData.Url;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public HomePage(CommonsBasePage lib) {
		super.lib = lib;
		PageFactory.initElements(lib.driver, this);
	}


	@FindBy(how = How.XPATH, using = "//input[@class='desktopOldAutosuggestTheme-input']")
	WebElement txtArama;
	@FindBy(how = How.CLASS_NAME, using = "SearchBoxOld-buttonContainer")
	WebElement btnAra;

	@FindBy(how = How.CSS, using = ".logo-hepsiburada")
	WebElement lblHepsiBurada;

	@FindBy(how = How.XPATH, using = "//div[@class='searchResultSummaryBar-mainText']")
	WebElement lblAramaSonuc;

	public HomePage anaSayfayaGit() {

		lib.navigateTo(Url.HEPSIBURADA_URL);

		lib.Control(lib.isElementExist(lblHepsiBurada), "'Ana Sayfa' sayfası açıldı.", "'Ana Sayfa' sayfası açılamadı!");
		return this;
	}


	public SearcPage aramaYap(String arama) {

		lib.sendKeys(txtArama, arama);
		lib.click(btnAra);
		lib.Control(lib.isElementExist(lblAramaSonuc), "'"
		                                               + arama
		                                               + "' için sonuç bulundu.", "'"
		                                                                          + arama
		                                                                          + "' için sonuç bulunamadı!");

		return new SearcPage(lib);
	}

}
