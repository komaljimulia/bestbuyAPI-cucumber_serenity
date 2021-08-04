package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.stepinfo.StoresInfo;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.core.IsEqual.equalTo;

public class StoreSteps {

    static String name = " Transfer Store:"  + TestUtils.getRandomValue();
    static String type = "Finance:" + TestUtils.getRandomValue();
    static String address = "91 Al Gate";
    static String address2 = "Baker Street";
    static String city = "London";
    static String state = "North";
    static String zip = "52525AB";
    static float lat = 23.123f;
    static float lng = -89.99f;

    static long storeId;


    @Steps
    StoresInfo storesInfo;




    @When("^User sends a GET request to the stores endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheStoresEndpointTheyMustGetBackAValidStatusCode() {
        storesInfo.getAllStores().log().all().statusCode(200);

    }

    @When("^I create a new stores by providing the information name type address address two city state zip lat \"([^\"]*)\" lng \"([^\"]*)\"$")
    public void iCreateANewStoresByProvidingTheInformationNameTypeAddressAddressTwoCityStateZipLatLng(float lat, float lng)  {
        storeId = storesInfo.createNewStore(name, type, address, address2, city, state, zip, lat).statusCode(201).log().all().extract().response()
                .body().jsonPath().getLong("id");

    }

    @Then("^I verify the stores is created$")
    public void iVerifyTheStoresIsCreated() {
        storesInfo.getStoreById(storeId).statusCode(200).log().all();

    }

    @When("^I send GET request to stores endpoint with Id \"([^\"]*)\" , I should received the store information$")
    public void iSendGETRequestToStoresEndpointWithIdIShouldReceivedTheStoreInformation(String id) {
        storesInfo.getStoreById(storeId).statusCode(200).log().all();

    }

    @When("^I update a created store providing the new name type and hours$")
    public void iUpdateACreatedStoreProvidingTheNewNameTypeAndHours() {
        name = name +"_Updated";
        type = type +"_Updated";

        storesInfo.updateStore(storeId,name, type).statusCode(200).log().all();


    }

    @Then("^I verify the store is updated$")
    public void iVerifyTheStoreIsUpdated() {
        storesInfo.getStoreById(storeId).body("name",equalTo(name));

    }

    @When("^I delete a created store ,they must get back a valid status code is 200$")
    public void iDeleteACreatedStoreTheyMustGetBackAValidStatusCodeIs() {
        storesInfo.deleteStore(storeId).statusCode(200).log().all();

    }

    @Then("^I verify the store is deleted$")
    public void iVerifyTheStoreIsDeleted() {
        storesInfo.getStoreById(storeId).statusCode(404).log().all();
    }
}
