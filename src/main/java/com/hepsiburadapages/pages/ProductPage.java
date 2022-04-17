package com.hepsiburadapages.pages;

import com.hepsiburadabase.commons.CommonsBasePage;
import com.hepsiburadabase.utility.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public ProductPage(CommonsBasePage lib) {
		super.lib = lib;
		PageFactory.initElements(lib.driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='reviewsTabTrigger']")
	WebElement btnDegerlendirmeler;
	@FindBy(how = How.XPATH, using = "(//div[@class='thumbsUp hermes-ReviewCard-module-1EWpv'])[1]")
	WebElement btnEvet;
	@FindBy(how = How.XPATH, using = "(//span[@class='hermes-ReviewCard-module-1ZiTv'])[1]")
	WebElement txtTesekkur;




	private void urunTabGecisi() {

		ArrayList<String> newTb = new ArrayList<>(lib.driver.getWindowHandles());
		//switch to new tab
		lib.driver.switchTo()
				.window(newTb.get(1));
		Log.pass("Açılan Ürün tabına geçildi.");
	}



	public ProductPage urunDegerlendir() {

		urunTabGecisi();
		lib.click(btnDegerlendirmeler);
		lib.scrollToElement(btnDegerlendirmeler);
		return this;

	}

	public ProductPage kontrolYorum() {
		List<WebElement> yorumlar = lib.findElements(By.xpath("//div[@class='hermes-ReviewCard-module-2dVP9']"));
		int yorumsayisi = yorumlar.size();
		if (yorumsayisi > 1)
			lib.click(btnEvet);
		lib.Control(lib.getTextOfElement((txtTesekkur)).contains("Teşekkür Ederiz."),
				"Teşekkür Ederiz. yazısı görüldü. Ürün değerlendirme başarılı", "Teşekkür Ederiz.yazısı görülmedi.Ürün değerlendirme kontrolü başarısız.");
		return this;
	}


	public ProductPage kontrolSiralaFltresi() {

		List<String> supplierNames = Arrays.asList("En yeni değerlendirme", "En faydalı değerlendirme","Puana göre artan","Puana göre azalan");
		lib.click(By.xpath("//div[@class='hermes-Sort-module-pGjws']"));
		List<WebElement> filtreDegerleri = lib.findElements(By.xpath("//div[@class='hermes-Sort-module-vYQvT hermes-Sort-module-2npZQ']"));
		int siralaFiltreSayisi = filtreDegerleri.size();

		for (int i = 0; i < siralaFiltreSayisi; i++) {

			if(!supplierNames.contains(filtreDegerleri.get(i).getText()))
			{
				Log.fail(filtreDegerleri.get(i).getText()+" değeri filtre listesinde bulunmamaktadır.");
			}
		}
		        Log.pass("Beklenen filtre değer listesi ile gelen filtre değer listesi eşleşmektedir." );
		return this;

	}
}