package com.trendyol.test.Services;

import com.trendyolbase.controller.TestController;
import com.trendyolpages.service.trendyolServices;
import io.qameta.allure.Step;
import org.testng.annotations.Test;


public class ServicesTest extends TestController {
  trendyolServices hs= new trendyolServices();


  @Step("Get Book API status")
  @Test(priority = 0)
  public void getAPIStatus() {

    hs.getApiStatus();

  }

    @Step("Get All Books")
    @Test(priority =1 )
    public void gelAllBooks() {

        hs.getAllBook();

    }

  @Step("Get Single Books")
  @Test
  public void getSinglelBooks() {

    hs.getSingleBook("1");

  }


  @Step("Get Not Found Book ID")
  @Test
  public void getNotFoundBookID() {

    hs.getSingleBook("10");

  }

  @Step("Get Single Books")
  @Test
  public void postAddAuthor() {

    hs.postAddAuthor();

 }

}