package com.trendyolpages.service;

import com.trendyolbase.basepages.ServicesBase;
import com.trendyolpages.requestpojo.BookRequestPojo;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class trendyolServices extends ServicesBase {

    BookRequestPojo request=new BookRequestPojo();
    String serviceUrl="https://simple-books-api.glitch.me";
    private  static final String bearerToken="ccc1720956c8930f80cbbe6c3635e9fe568372d2f39284d4afd597b047f751b2";
    
    public trendyolServices getApiStatus() {
        getRequest(serviceUrl, "", 200);
        return this;
    }
    public trendyolServices getAllBook() {
        getRequest(serviceUrl, "/books", 200);
        return this;
    }

    public trendyolServices getSingleBook(String bookId) {
       Response response= getRequest(serviceUrl, "/books/"+bookId+"", 200);
       String bookIdResponse=response.jsonPath().getString("id");
       checkExpectedValue(bookIdResponse.equals(bookId),"Aranan book id değeri bulundu..." + "Beklenen değer:" + bookId + "Servisten dönen değer:" + bookIdResponse,"Hata! Beklenen değer gelmedi"+ "Aranan book id:" + bookId);
        return this;
    }


    public trendyolServices postAddAuthor() {

        request.setBookId(3);
        String requestBody = new Gson().toJson(request);
        postWithTokenRequest(bearerToken,serviceUrl, "/orders",requestBody, 201).asString();
        return this;
    }


    public trendyolServices postAddAuthor1() {

        request.setBookId(3);
        String requestBody = new Gson().toJson(request);
        postWithTokenRequest(bearerToken,serviceUrl, "/orders",requestBody, 201).asString();
        return this;
    }









}