package com.bestbuy.cucumber.stepdefs;

import com.bestbuy.stepinfo.ServicesInfo;
import com.bestbuy.utils.TestUtils;
import com.opera.core.systems.scope.protos.ScopeProtos;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.core.IsEqual.equalTo;

public class ServiceSteps {

    static String name = "Hand Car Wash"  +  TestUtils.getRandomValue();
    static long servicesId;

    @Steps
    ServicesInfo servicesInfo;



    @When("^User sends a GET request to the services endpoint , they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheServicesEndpointTheyMustGetBackAValidStatusCode() {
        servicesInfo.getAllServices().log().all().statusCode(200);
        
    }

    @When("^I create a new services by providing the information name$")
    public void iCreateANewServicesByProvidingTheInformationName() {
        servicesId = servicesInfo.createNewServices(name).log().all().statusCode(201).extract().response()
                .body().jsonPath().getLong("id");

        
    }

    @Then("^I verify the services is created$")
    public void iVerifyTheServicesIsCreated() {
        servicesInfo.getServicesById(servicesId).log().all().statusCode(200).log().all();
        
    }

    @When("^I send GET request to services endpoint with Id \"([^\"]*)\" , I should received the services information$")
    public void iSendGETRequestToServicesEndpointWithIdIShouldReceivedTheServicesInformation(String id)  {
        servicesInfo.getServicesById(servicesId).log().all().statusCode(200).log().all();

    }

    @When("^I update a created services providing the new name$")
    public void iUpdateACreatedServicesProvidingTheNewName() {
        name = name+"_Updated";

        servicesInfo.updateServices(servicesId,name).statusCode(200).log().all();
        
    }

    @Then("^I verify the services is updated$")
    public void iVerifyTheServicesIsUpdated() {
        servicesInfo.getServicesById(servicesId).body("name",equalTo(name));
        
    }

    @When("^I delete a created services ,they must get back a valid status code is 200")
    public void iDeleteACreatedServicesTheyMustGetBackAValidStatusCodeIs() {
        servicesInfo.deleteServicesById(servicesId).statusCode(200).log().all();

    }

    @Then("^I verify the services is created is deleted$")
    public void iVerifyTheServicesIsCreatedIsDeleted() {
        servicesInfo.getServicesById(servicesId).statusCode(404).log().all();
    }
}
