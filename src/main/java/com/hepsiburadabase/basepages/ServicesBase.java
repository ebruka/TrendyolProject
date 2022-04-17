package com.hepsiburadabase.basepages;

import com.hepsiburadabase.utility.Log;
import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

import static io.restassured.RestAssured.given;

public class ServicesBase{

    Response response;

    public Response getRequest(String url, String serviceName, int statusCode) {

        try {
            RequestSpecification requestSpec = serviceHeaders();

            response = given().spec(requestSpec)
                    .when()
                    .get(url + serviceName)
                    .then()
                    .extract()
                    .response();

            Log.pass(serviceName + " Servisi Response: " + response.asString());
            checkExpectedValue(response.getStatusCode() == statusCode, serviceName
                    + " Servisi Status Code:"
                    + statusCode
                    + " kontrolu basarılı", serviceName
                    + " Servisi Status Code:"
                    + statusCode
                    + " kontrolu başarısız. Servisten dönen Status: "
                    + response.getStatusLine());
        } catch (Exception e) {
            Log.fail("açıklama" + e);
        }
        return response;
    }


    /**
     * Post API servisini çağırıp response u string olarak döner.
     *
     * @return string response.
     */
    public Response postRequest(String url, String serviceName, String requestbody, int statusCode) {


        try {
            RequestSpecification requestSpec = serviceHeaders();

            response = given().spec(requestSpec)
                    .body(requestbody)
                    .when()
                    .post(url+serviceName)
                    .then()
                    .extract()
                    .response();

       //     Log.pass(serviceName + " Servisi Response: " + response.asString());
            checkExpectedValue(response.getStatusCode() == statusCode, serviceName
                    + " Servisi Status Code:"
                    + statusCode
                    + " kontrolu basarılı", serviceName
                    + " Servisi Status Code:"
                    + statusCode
                    + " kontrolu başarısız. Servisten dönen Status: "
                    + response.getStatusLine());
        } catch (Exception e) {
            Log.fail("açıklama" + e);

        }
        return response;
    }


    protected RequestSpecification serviceHeaders() {

        return new RequestSpecBuilder().setContentType("application/json").addFilter(new AllureRestAssured())
                .addHeader("accept", "application/json, application/xml, text/json, text/x-json, text/javascript, text/xml")
                .setRelaxedHTTPSValidation()
                .build();
    }



    /**
     *
     * @param body request veya response body
     * @param path değerini almak istediğimiz elementin pathi
     * @param index değerini almak istediğimiz elementin index değeri
     * @return
     */
    public String getAttributeValue(String body,String path,Integer index) {

        List<String> attributeValue = new ArrayList<>();

        try {
            attributeValue = JsonPath.parse(body)
                    .read(path);
            return attributeValue.get(index);
        } catch (Exception e) {
            // LogINFO("Parse Error " + e);
            e.printStackTrace();
        }
        return attributeValue.get(index);
    }



    public void checkExpectedValue(boolean statement, String onTrue, String onFalse) {

        if (statement) {
           Log.pass(onTrue);
        } else {
           Log.fail(onFalse);
        }
    }


}