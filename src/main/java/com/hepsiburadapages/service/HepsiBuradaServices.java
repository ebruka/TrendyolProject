package com.hepsiburadapages.service;

import com.hepsiburadabase.basepages.ServicesBase;
import com.hepsiburadapages.request.GroceryRequestPojo;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class HepsiBuradaServices extends ServicesBase {

    GroceryRequestPojo request=new GroceryRequestPojo();
    String serviceUrl="https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/";

    public HepsiBuradaServices getAllGrocery() {
        getRequest("https://a19b4e32-1267-44ab-a421-186ff6b27cd5.mock.pstmn.io/", "allGrocery", 200);
        return this;
    }


    public HepsiBuradaServices postAddGrocery() {

        request.setId(4);
        request.setName("orange");
        request.setPrice(12.3);
        request.setStock(3);
        String requestBody = new Gson().toJson(request);
        postRequest(serviceUrl, "allGrocery/add",requestBody, 200).asString();
        return this;
    }

    public HepsiBuradaServices checkFruitName(String fruit) {


        Response  response = getRequest(serviceUrl, "allGrocery/" + fruit, 200);
        String fruitName=getAttributeValue(response.asString(),"$.data[*].name",0);
        checkExpectedValue(fruitName.equals(fruit),"Beklenen değerle eşleşti" + "Beklenen değer:" + fruit + "Servisten dönen değer:" + fruitName,"Hata! Beklenen değer gelmedi"+ "Beklenen değer:" + fruit);
        return this;
    }


    public HepsiBuradaServices checkNotFoundFruitName(String fruit) {

         getRequest(serviceUrl, "allGrocery/" + fruit, 404);

        return this;

    }




}