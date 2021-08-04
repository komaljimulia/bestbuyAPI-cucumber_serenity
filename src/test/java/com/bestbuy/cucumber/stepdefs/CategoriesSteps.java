package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.stepinfo.CategoriesInfo;
import com.bestbuy.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.core.IsEqual.equalTo;

public class CategoriesSteps {


    @Steps
    CategoriesInfo categoriesInfo;

    static String name = "Sport Equipment" + TestUtils.getRandomValue();
    static String id = "SE006" + TestUtils.getRandomValue();
    static String categoriesId;


    @When("^user send a GET request to categories endpoint,they must get back a valid status code 200$")
    public void userSendAGETRequestToCategoriesEndpointTheyMustGetBackAValidStatusCode() {
        categoriesInfo.getAllCategories().log().all().statusCode(200);

    }

    @When("^I create new categories by providing the information name and id$")
    public void iCreateNewCategoriesByProvidingTheInformationNameAndId() {
        categoriesId = categoriesInfo.createNewCategories(name, id).log().all().statusCode(201).extract().response()
                .body().jsonPath().getString("id");

    }

    @When("^I send GET request to endpoint with id \"([^\"]*)\", I should received the categories information$")
    public void iSendGETRequestToEndpointWithIdIShouldReceivedTheCategoriesInformation(String id) {
        categoriesInfo.getCategoriesById(categoriesId).log().all().statusCode(200);

    }

    @When("^I update a created categories providing the new name$")
    public void iUpdateACreatedCategoriesProvidingTheNewName() {
        name = name + "_Updated";

        categoriesInfo.updateCategories(categoriesId, name).statusCode(200).log().all();

    }

    @Then("^I verify the categories is updated$")
    public void iVerifyTheCategoriesIsUpdated() {
        categoriesInfo.getCategoriesById(categoriesId).body("name", equalTo(name));

    }

    @When("^I delete a created categories ,they must get back a valid status code is 200$")
    public void iDeleteACreatedCategoriesTheyMustGetBackAValidStatusCodeIs() {
        categoriesInfo.deleteCategoriesById(categoriesId).statusCode(200).log().all();

    }

    @Then("^I verify the categories is deleted$")
    public void iVerifyTheCategoriesIsDeleted() {
        categoriesInfo.getCategoriesById(categoriesId).statusCode(404).log().all();
    }
}

