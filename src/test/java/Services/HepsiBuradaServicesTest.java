package Services;
import com.hepsiburadabase.controller.TestController;
import com.hepsiburadapages.service.HepsiBuradaServices;
import io.qameta.allure.Step;

import org.testng.annotations.Test;


public class HepsiBuradaServicesTest extends TestController {
  HepsiBuradaServices hs= new HepsiBuradaServices();


    @Step("Get All Grocery")
    @Test
    public void getAllGroceryServicesTest() {

        hs.getAllGrocery();

    }


    @Step("Add Grocery")
    @Test
    public void addGroceryServicesTest() {

        hs.postAddGrocery();

    }

    @Step("Check Fruit Name")
    @Test
    public void checkFruitNameServicesTest() {

        hs.checkFruitName("apple");

    }


    @Step("Check Not Found Fruit Name")
    @Test
    public void checkNotFoundFruitNameServicesTest() {

        hs.checkNotFoundFruitName("orange");

    }


}