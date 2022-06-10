package com.trendyolpages.pages;


import com.denemebase.commons.CommonsBasePage;
import com.trendyolbase.data.GetData.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public HomePage(CommonsBasePage lib) {
		super.lib = lib;
		PageFactory.initElements(lib.driver, this);
	}



	@FindBy(how = How.XPATH, using = "//a[contains(@href,'boutique')]")
	WebElement linkTrendyolButik;

	@FindBy(how = How.XPATH, using = "//*[@title='trendyol']")
	WebElement lblTrendyol;

	@FindBy(how = How.XPATH, using = "//*[@class='account-nav-item user-login-container']")
	WebElement btnGirisYap;

	@FindBy(how = How.ID, using = "login-email")
	WebElement txtemail;

	@FindBy(how = How.ID, using = "login-password-input")
	WebElement txtsifre;

	@FindBy(how = How.XPATH, using = "//*[@type='submit']")
	WebElement btnLogin;

	@FindBy(how = How.XPATH, using = "//*[@class='link account-user']")
	WebElement btnHesabim;

	@FindBy(how = How.XPATH, using = "//*[@class='message']")
	WebElement textWarningMessage;

	@FindBy(how = How.ID, using = "homepage-popup-text")
	WebElement popUphomePage;

	@FindBy(how = How.XPATH, using = "//*[@class='modal-close']")
	WebElement btnModalClosed;

	@FindBy(how = How.XPATH, using = "//*[@class='q-icon icon-facebook i-facebook']")
	WebElement btnLoginFacebook;

	@FindBy(how = How.XPATH, using = "//*[@class='q-icon icon-google i-google']")
	WebElement btnLoginGoogle;

	@FindBy(how = How.ID, using = "email")
	WebElement txtFacebookEmail;
	@FindBy(how = How.ID, using = "pass")
	WebElement txtFacebookSifre;
	@FindBy(how = How.ID, using = "loginbutton")
	WebElement btnFacebookGirisYap;

	@FindBy(how = How.XPATH, using = "//*[@class='BHzsHc']")
	WebElement btnGoogleBaskaHesap;

	@FindBy(how = How.ID, using = "identifierId")
	WebElement txtGoogleEmail;
	@FindBy(how = How.XPATH, using = "(//*[@jsname='V67aGc'])[2]")
	WebElement btnIleri;
	@FindBy(how = How.NAME, using = "password")
	WebElement txtGoogleSifre;



	public HomePage linkControl() throws IOException {
		List<String[]> dataLines = new ArrayList<>();
		List<WebElement> butikLink=lib.findElements(By.xpath("//a[contains(@href,'boutique')]"));
		int linkSayisi=butikLink.size();

		for (int i = 0; i < linkSayisi; i++) {
			String url=	butikLink.get(i).getAttribute("href");
			String urlControl= lib.brokenUrlControl(url);
			dataLines.add(new String[] {urlControl});


			}

             lib.writeCSV(dataLines,"csvdata");

		return this;
	}


	public HomePage anaSayfayaGit() {

		lib.navigateTo(Url.TRENDYOL_URL);
		if(lib.isElementExist(popUphomePage))
			lib.click(btnModalClosed);
		lib.Control(lib.isElementExist(lblTrendyol), "'Ana Sayfa' sayfası açıldı.", "'Ana Sayfa' sayfası açılamadı!");
		return this;
	}


	public HomePage uyeGirisiYap(String email,String sifre) {

		lib.click(btnGirisYap);
		lib.sendKeys(txtemail,email);
		lib.sendKeys(txtsifre,sifre);
		lib.click(btnLogin);
		lib.Control(lib.isElementExist(btnHesabim),"Üye Girisi Yapildi...","Üye Girisi Yapilirken Hata ALindi...");

		return this;
	}


	public HomePage facebookGirisi() {

		lib.click(btnGirisYap);
		lib.click(btnLoginFacebook);
		lib.Control(lib.isElementExist(txtFacebookEmail),"Facebook login sayfasına yönlendirildi...","Facebook login sayfasına yönlendirilirken hata alindi...");
		return this;
	}

	public HomePage googleGirisi() {
        lib.click(btnGirisYap);
		lib.click(btnLoginGoogle);
		lib.Control(lib.isElementExist(btnGoogleBaskaHesap),"Google login sayfasına yönlendirildi...","Google login sayfasına yönlendirilirken hata alindi...");
		lib.click(btnGoogleBaskaHesap);
		return this;
	}


	public HomePage controlWarningMessage() {

		lib.Control(lib.isElementExist(textWarningMessage),textWarningMessage.getText(),"Login işlemi yapılırken hata mesajı kontrolü yapilamadi..");

		return this;
	}



}
