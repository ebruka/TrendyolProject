package com.trendyolpages.pages;


import com.trendyolbase.commons.CommonsBasePage;
import com.trendyolbase.basepages.ServicesBase;
import com.trendyolbase.data.GetData.Url;
import com.trendyolbase.utility.Log;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


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



	public HomePage linkControl()  {
		List<String[]> dataLines = new ArrayList<>();
		ServicesBase services=new ServicesBase();
		List<WebElement> butikLink=lib.findElements(By.xpath("//a[contains(@href,'boutique')]"));
		int linkSayisi=butikLink.size();

		for (int i = 0; i < linkSayisi; i++) {
			String url=	butikLink.get(i).getAttribute("href");
		    Response response=services.getRequest(url,"", HttpStatus.SC_OK);
			dataLines.add(new String[] {"URL Bilgisi:" + " " + url,"Status Kodu:" + "" +Integer.toString(response.getStatusCode()),"Response Time:" + " " +Long.toString(response.getTime())});
			}

             lib.writeCSV(dataLines,"csvdata");

		return this;
	}




	public HomePage butikImagelinkControl()  {
		List<String[]> dataLines = new ArrayList<>();
		ServicesBase services=new ServicesBase();
		JavascriptExecutor js = (JavascriptExecutor) lib.driver;
		//Scroll down till the bottom of the page
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		List<WebElement> butikLink=lib.findElements(By.xpath("//*[@class='image-container']//img"));
		int linkSayisi=butikLink.size();

		for (int i = 0; i < linkSayisi; i++) {
			String url=	butikLink.get(i).getAttribute("src");
			Response response=services.getRequest(url,"", HttpStatus.SC_OK);
			dataLines.add(new String[] {"URL Bilgisi:" + " " + url,"Status Kodu:" + "" +Integer.toString(response.getStatusCode()),"Response Time:" + " " +Long.toString(response.getTime())});
		}

		lib.writeCSV(dataLines,"csvdata");

		return this;
	}






	public HomePage anaSayfayaGit() {

		lib.navigateTo(Url.TRENDYOL_URL);
		if(lib.isElementExist(popUphomePage))
			lib.click(btnModalClosed);
		lib.Control(lib.isElementExist(lblTrendyol), "'Ana Sayfa' sayfas?? a????ld??.", "'Ana Sayfa' sayfas?? a????lamad??!");
		return this;
	}


	public HomePage uyeGirisiYap(String email,String sifre) {

		lib.click(btnGirisYap);
		lib.sendKeys(txtemail,email);
		lib.sendKeys(txtsifre,sifre);
		lib.click(btnLogin);
		lib.Control(lib.isElementExist(btnHesabim),"??ye Girisi Yapildi...","??ye Girisi Yapilirken Hata ALindi...");

		return this;
	}


	public HomePage facebookGirisi() {

		lib.click(btnGirisYap);
		lib.click(btnLoginFacebook);
		lib.Control(lib.isElementExist(txtFacebookEmail),"Facebook login sayfas??na y??nlendirildi...","Facebook login sayfas??na y??nlendirilirken hata alindi...");
		return this;
	}

	public HomePage googleGirisi() {
        lib.click(btnGirisYap);
		lib.click(btnLoginGoogle);
		lib.Control(lib.isElementExist(txtGoogleEmail),"Google login sayfas??na y??nlendirildi...","Google login sayfas??na y??nlendirilirken hata alindi...");
		lib.click(btnGoogleBaskaHesap);
		return this;
	}


	public HomePage controlWarningMessage() {

		lib.Control(lib.isElementExist(textWarningMessage),textWarningMessage.getText(),"Login i??lemi yap??l??rken hata mesaj?? kontrol?? yapilamadi..");
		return this;
	}



}
