package com.trendyolbase.basepages;

import com.trendyolbase.data.DataFinder;
import com.trendyolbase.data.GetData;
import com.trendyolbase.utility.Driver;
import com.trendyolbase.utility.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeleniumBasePage {

	public final WebDriver driver;
	protected WebDriverWait wait, waitZero, waitLoader;

	public SeleniumBasePage() {
		this.driver = Driver.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT));
		this.waitZero = new WebDriverWait(driver, Duration.ofSeconds(0));
		this.waitLoader = new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT_LOADERBOX));
	}

	/** Control statement
	 * @param onTrue    Expected result message
	 * @param onFalse   False result message
	 */
	public void Control(boolean statement, String onTrue, String onFalse) {

		if (statement) {
			Log.pass(onTrue);
		} else {
			Log.fail(onFalse);
		}
	}

	/**
	 *Default Wait Method
	 */
	public void Wait(int millisecond) {

		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void navigateTo(GetData.Url url) {

		try {
			driver.get(DataFinder.getUrl(url));
			driver.manage()
					.timeouts()
					.pageLoadTimeout(Duration.ofSeconds(GetData.DEFAULT_WAIT_LOADERBOX));
			Log.pass("Web sayfası açıldı: " + DataFinder.getUrl(url));
		} catch (Exception e) {
			Log.fail("Error while getting app url : " + e);
			Log.fail("Error while getting app url : " + e);

			throw new RuntimeException(e);
		}
	}




	/**
	 *
	 * Use this method to get current url
	 *
	 * @return
	 */
	public String getUrl(){
		String url = null;

		try {
			url = driver.getCurrentUrl();
			Log.pass("Url : " +url);
			Log.pass("Url bilgisi başarıyla alındı...");
		} catch (Exception e){
			Log.fail("Url bilgisi alınamadı!...", e);
		}

		return url;
	}


	/**
	 *
	 * Use this method to scroll to an element.
	 *
	 * @param element
	 */
	public void scrollToElement(WebElement element,String whereToScroll) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			Log.pass("Objeye başarıyla scroll edildi : " + whereToScroll);
		} catch (Exception e){
			Log.fail("Error while scrolling to the element : ", e);
		}

	}
	public void scrollToElement (WebElement element){
		scrollToElement(element,getTextOfElement(element));
	}

	/**
	 * Use this method to find element by cssSelector
	 * 
	 * @param by
	 * @param index
	 * @return A WebElement, or an empty if nothing matches @
	 */
	public WebElement findElement(By by, int... index) {
		// driver.manage().timeouts().implicitlyWait(GetData.DEFAULT_WAIT,

		// TimeUnit.SECONDS);

		WebElement element = null;
		untilElementAppear(by);
		try {
			if (index.length == 0)
				element = driver.findElement(by);
			else
				element = driver.findElements(by)
				                .get(index[0]);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();", element);
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].focus();", element);
			// wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			Log.fail("Error while clicking webelement : ", e);
		}
		return element;
	}

	/**
	 * Wait for element toBeClickable
	 */
	public WebElement waitForElementClickable(WebElement element) {

		return new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT)).until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElement(By by, int... index) {

		waitLoaderBox();
		findElement(by, index);
	}

	public WebElement waitForElement(WebElement element) {

		return new WebDriverWait(driver, Duration.ofSeconds(GetData.DEFAULT_WAIT)).until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElement(WebElement element, int seconds) {

		return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOf(element));
	}

	public void waitLoaderBox() {

		waitLoaderBox(GetData.DEFAULT_WAIT_LOADERBOX);// 90
	}


	public void waitLoaderBox(int time) {

		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(0));
		if (!driver.findElements(By.xpath("//div[starts-with(@class,'loader')]"))
		          .isEmpty()) {
			driver.manage()
			      .timeouts()
			      .implicitlyWait(Duration.ofSeconds(time));
			driver.findElement(By.xpath("//div[@class='loader' and @style='display: none;']"));
			driver.findElement(By.xpath("//div[@class='loader-box' and @style='display: none;']"));
		}
		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));
	}


	public boolean isClickable(WebElement element){

		try{
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}catch (Exception e){
			return false;
		}

	}

	/*
	 * Use this method click to element
	 * 
	 * @param by
	 * @param index @
	 */
	public void click(By by, int... index) {
		click(by,null, index);
	}

	/*
	 * Use this method click to element
	 *
	 * @param by
	 * @param message Log'a basılacak mesaj
	 * @param index @
	 */
	public void click(By by, String message, int... index) {
		WebElement element;
		try {
			element = findElement(by, index);
			String elemText = element.getText();
			element.click();
			Log.pass("Click Button : " + (message != null ? message : elemText));
		} catch (Exception e) {
			Log.fail("Error while clicking webelement : ", e);
		}
	}

	/*
	 * Use this method click to element
	 *
	 * @param by
	 * @param index @
	 */
	public void click(WebElement element) {
		click(element,null);
	}

	/*
	 * Use this method click to element
	 *
	 * @param by
	 * @param message Log'a basılacak mesaj
	 * @param index @
	 */
	public void click(WebElement element, String message) {

		String elemText = "";
		try {
			elemText = element.getText();
			element.click();
			Log.pass("Click Button : " + (message != null ? message : elemText));

		}
		catch (WebDriverException e) {
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});arguments[0].focus();", element);
				element.click();
				Log.pass("Click Button : " + (message != null ? message : elemText));
			}catch (Exception exception) {
				Log.fail("Error while clicking webelement " + (message != null ? message : elemText) + " : ", e);
			}
		} catch (Exception e) {
			Log.fail("Error while clicking webelement " + (message != null ? message : elemText) + " : ", e);
		}
	}
	

	/*
	 * Use this method click to element
	 * 
	 * @param by
	 * @param index @
	 */
	public void click(By by, boolean clickable) {
		click(by, clickable, null);
	}

	/*
	 * Use this method click to element
	 *
	 * @param by
	 * @param message Log'a basılacak mesaj
	 * @param index @
	 */
	public void click(By by, boolean clickable, String message) {

		try {
			if (!clickable)
				click(by, message);
			else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				WebElement elem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
				String elemText = elem.getText();
				elem.click();
				Log.pass("Click Button : " + (message != null ? message : elemText));
			}
		} catch (Exception e) {
			Log.fail("Error while clicking webelement : ", e);
		}
	}




	public boolean isElementExist(By by) {

		return isElementExist(by, 15);
	}

	public boolean isElementExist(By by, int timeSeconds) {

		driver.manage()
				.timeouts()
				.implicitlyWait(Duration.ofSeconds(timeSeconds));
		boolean isExist = driver.findElements(by)
				.size() > 0;
		driver.manage()
				.timeouts()
				.implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));

		return isExist;
	}


	public boolean isElementExist(WebElement element) {

		return isElementExist(getByOfElement(element), 15);
	}

	public boolean isElementExist(WebElement element, int timeSeconds) {

		return isElementExist(getByOfElement(element), timeSeconds);
	}


	/*
	 * Use this method to click to an element that is actually clickable/interactable.
	 *
	 *
	 * @param element
	 */
	public void waitAndClickableElement(By element){
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e){
			Log.fail("Error while clicking web element: ", e);
		}
	}
	public void waitAndClickableElement(WebElement element){
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e){
			Log.fail("Error while clicking web element: ", e);
		}
	}

	/*
	 * Use this method to simulate typing into an element if it is enable. Send enter if pressEnter is
	 * true, do nothing otherwise. Note : Before sending operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter @
	 */
	public void sendKeys(By by, String text, boolean pressEnter, int... index) {

		WebElement element;
		String elemText = null;
		try {
			element = findElement(by, index);
			if (element.isEnabled()) {
				elemText = element.getText();
				element.clear();
				element.sendKeys(text);
				if (pressEnter) {
					waitLoaderBox();
					element.sendKeys(Keys.ENTER);
				}
			}
			Log.pass("Value : " + text + " - SendKeys : " + elemText);
		} catch (Exception e) {
			Log.fail("Error while filling field : ", e);
		}
	}

	/*
	 * Use this method to simulate typing into an element if it is enable. Send enter if pressEnter is
	 * true, do nothing otherwise. Note : Before sending operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter @
	 */
	public void sendKeys(WebElement element, String text, boolean pressEnter) {

		String elemText = null;
		try {
			if (element.isEnabled()) {
				elemText = element.getText();
				//				element.clear();
				element.sendKeys(text);
				if (pressEnter) {
					waitLoaderBox();
					element.sendKeys(Keys.ENTER);
				}
			}
			Log.pass("Value : " + text + " - SendKeys : " + elemText);
		} catch (Exception e) {
			Log.fail("Error while filling field : ", e);
		}
	}

	/*
	 * Use this method to simulate typing into an element if it is enable. Send enter if pressEnter is
	 * true, do nothing otherwise. Note : Before sending operation, element is cleared.
	 * 
	 * @param by
	 * @param text
	 * @param pressEnter @
	 */
	public void sendKeys(By by, Keys key, int... index) {

		WebElement element;
		String elemText = null;
		try {
			element = findElement(by, index);
			if (element.isEnabled()) {
				elemText = element.getText();
				element.sendKeys(key);
			}
			Log.pass("Value : " + key.toString() + " - SendKeys : " + elemText);
		} catch (Exception e) {
			Log.fail("Error while filling field : ", e);
		}
	}


	/*
	 * Use this method to simulate typing into an element if it is enable. Note : Before sending
	 * operation, element is cleared.
	 * 
	 * @param by
	 * @param text @
	 */
	public void sendKeys(By by, String text, int... index) {

		sendKeys(by, text, false, index);
	}

	/*
	 * Use this method to simulate typing into an element if it is enable. Note : Before sending
	 * operation, element is cleared.
	 * 
	 * @param by
	 * @param text @
	 */
	public void sendKeys(WebElement element, String text) {

		sendKeys(element, text, false);
	}







	/*
	 * Get the visible (i.e. not hidden by CSS) innerText of this element.
	 * 
	 * @param by
	 * @param index
	 * @return The innerText of this element. @
	 */
	public String getTextOfElement(By by, int... index) {

		String text = null;
		untilElementAppear(by);

		try {
			if (index.length == 0)

				text = driver.findElement(by)
				             .getText();
			else
				text = driver.findElements(by)
				             .get(index[0])
				             .getText();
		} catch (Exception e) {
			Log.fail("Error while getting text of element : ", e);
		}
		return text;
	}




	/**
	 * Normalde texti olmasına rağmen alamadığımız durumlarda
	 * textin gelmesini bekletiyoruz.
	 * @param element Textini almak istediğimiz webelement
	 */
	public ExpectedCondition<Boolean> presenceOfText(WebElement element) {
		return webDriver -> !element.getText().isEmpty();
	}


	//@SuppressWarnings("finally")
	public String getTextOfElement(WebElement elem) {

		String text = null;
		try {
			text = elem.getText();
			Log.pass(text);
		} catch (Exception exception) {
			Log.fail("Error while getting text of element: ", exception);
		}
		return text;
	}

	/*
	 * Wait until element appears
	 * 
	 * @param by
	 * @param index
	 */
	protected void untilElementAppear(By by) {

		try {
			// waitLoaderBox(90);// , 40
			// Thread.sleep(1000);
			// driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			// wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
			Log.fail("Error while waiting until element appears : ", e);
		}
	}

	public List<WebElement> presenceOfAllElements(By element){

		List<WebElement> elements;

		elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));

		return elements;
	}


	/**
	 * Wait until element disappears
	 * 
	 * @param by //
	 */
	protected void untilElementDisappear(By by) {

		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (Exception e) {
			Log.fail("Error while waiting until element disappears : ", e);
		}
	}

	/*
	 * Return true if element exist, false otherwise.
	 * 
	 * @param by
	 * @param index
	 * @return True if element exists, false otherwise.
	 */
	public boolean isElementExist(List<WebElement> elem) {

		return isElementExist(elem, 15);
	}

	public boolean isElementExist(List<WebElement> elem, int timeSeconds) {


		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(timeSeconds));
		boolean isExist = !elem.isEmpty();
		driver.manage()
		      .timeouts()
		      .implicitlyWait(Duration.ofSeconds(GetData.DEFAULT_WAIT));

		return isExist;
	}

	public String getProperty(By by, String expectedPropertyName, int... index) {

		WebElement elem;

		if (index.length == 0)
			elem = driver.findElement(by);
		else
			elem = driver.findElements(by)
			             .get(index[0]);

		return elem.getAttribute(expectedPropertyName);
	}

	public String getProperty(WebElement elem, String expectedPropertyName) {

		return elem.getAttribute(expectedPropertyName);
	}

	public List<WebElement> findElements(By by) {

		List<WebElement> webElements = null;
		untilElementAppear(by);
		try {
			webElements = driver.findElements(by);
		} catch (Exception e) {
			Log.fail("Error while listing webelements by css selector : ", e);
		}
		return webElements;
	}


	//frameler arası geçişi sağlar.
	public void switchToFrame(By by){

		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		WebElement iframe = wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
		driver.switchTo().frame(iframe);

	}


	public void controlElementText(WebElement elem, String onTrue, String onFalse) {

		try {
			if (!getTextOfElement(elem).contains(onTrue)) {
				Log.fail(onFalse);
			}
		} catch (Exception e) {
			Log.fail(onFalse, e);
		}
	}



	public void scrollToElementBlockCenter(WebElement element) {

		scrollToElementBlockCenter(element,getTextOfElement(element));

	}


	public void scrollToElementBlockCenter(WebElement element, String whereToScroll) {

		//Elementi merkez alarak scroll eder.
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
			Log.pass("Scroll işleminin başarıyla gerçekleştiği obje :  " + whereToScroll);
		} catch (Exception e){
			Log.fail("Error while scrolling to the element : ", e);
		}

	}


	public By getByOfElement(WebElement element) {

		try {
			driver.manage()
					.timeouts()
					.implicitlyWait(0, TimeUnit.SECONDS);
			String byString = element.toString();
			if (byString.contains(" -> "))
				byString = byString.split(" -> ")[1];
			else
				byString = byString.split("By.")[1];
			int byStringLenght = byString.length();
			String[] data = byString.substring(0, byStringLenght - 1)
					.split(": ");
			String locator = data[0];
			String term = "";
			for (int i = 1; i < data.length; i++)
				term += data[i] + ": ";
			term = term.substring(0, term.length() - 2);

			switch (locator) {
				case "xpath":
					return By.xpath(term);
				case "css selector":
					return By.cssSelector(term);
				case "id":
					return By.id(term);
				case "tag name":
					return By.tagName(term);
				case "name":
					return By.name(term);
				case "link text":
					return By.linkText(term);
				case "class name":
					return By.className(term);
			}
			throw new RuntimeException();
		} catch (Exception e) {
			Log.fail("getByOfElement hata aldı. Hata: " + e.getMessage());
			return null;
		} finally {
			driver.manage()
					.timeouts()
					.implicitlyWait(GetData.DEFAULT_WAIT, TimeUnit.SECONDS);
		}
	}




	public String convertToCSV(String[] data) {
		return Stream.of(data)
				.map(this::escapeSpecialCharacters)
				.collect(Collectors.joining(","));
	}

	public String escapeSpecialCharacters(String data) {
		String escapedData = data.replaceAll("\\R", " ");
		if (data.contains(",") || data.contains("\"") || data.contains("'")) {
			data = data.replace("\"", "\"\"");
			escapedData = "\"" + data + "\"";
		}
		return escapedData;
	}


     public void writeCSV(List<String[]> dataLines,String path)
	{
	File csvOutputFile = new File(path);
		try (
	PrintWriter pw = new PrintWriter(csvOutputFile)) {
			dataLines.stream()
					.map(this::convertToCSV)
					.forEach(pw::println);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


}

