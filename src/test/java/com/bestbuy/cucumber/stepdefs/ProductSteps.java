package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.stepinfo.ProductsInfo;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.core.IsEqual.equalTo;

public class ProductSteps {
    static long productid;

    static String name = "Nokia ipod:"  + TestUtils.getRandomValue();
    static String type = "Entertainment:"  + TestUtils.getRandomValue();
    static double price = 13.99;
    static String upc = "1459687694";
    static double shipping = 11.99;
    static String description = "Universal";
    static String manufacturer = "UK power";
    static String model = "String";
    static String url = "String";
    static String image = "String";

@Steps
    ProductsInfo productsInfo;


    @When("^User sends a GET request to products endpoint, they must get back a valid status code 200$")
    public void userSendsAGETRequestToProductsEndpointTheyMustGetBackAValidStatusCode() {
        productsInfo.getAllProducts().statusCode(200);

    }

    @When("^I create a new product by providing the information name type price\"([^\"]*)\" upc Shipping descriptions\"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypePriceUpcShippingDescriptions(double price, String descriptions) {
        productid= productsInfo.createNewProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image).statusCode(201).extract().response()
                .body().jsonPath().getLong("id");


    }

    @Then("^I verify the product is created$")
    public void iVerifyTheProductIsCreated() {
        productsInfo.getProductByID(productid).log().all().statusCode(200);

    }

    @When("^I send the Get request to product endpoint with ID \"([^\"]*)\",I should received the product information$")
    public void iSendTheGetRequestToProductEndpointWithIDIShouldReceivedTheProductInformation(String id)  {
        productsInfo.getProductByID(productid).log().all().statusCode(200);


    }

    @When("^I update a created product providing the new name upc price and description$")
    public void iUpdateACreatedProductProvidingTheNewNameUpcPriceAndDescription() {
        name = name + "_Updated";
        price = 99.99;
        upc = upc + "_added";
        productsInfo.updateProduct(productid, name, type, upc, price, description, model).log().all().statusCode(200);

    }

    @Then("^I verify the product is updated$")
    public void iVerifyTheProductIsUpdated() {
        productsInfo.getProductByID(productid).body("name", equalTo(name));

    }

    @When("^I delete a created product ,they must get back a valid status code  is 200$")
    public void iDeleteACreatedProductTheyMustGetBackAValidStatusCodeIs() {
        productsInfo.deleteProduct(productid).statusCode(200);

    }

    @Then("^I verify the product is deleted$")
    public void iVerifyTheProductIsDeleted() {
        productsInfo.getProductByID(productid).statusCode(404);
    }


    @Then("^I verify the categories is created$")
    public void iVerifyTheCategoriesIsCreated() {
        
    }
}

