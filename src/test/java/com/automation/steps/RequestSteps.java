package com.automation.steps;

import com.automation.pojo.DataPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class RequestSteps {

    @Given("user wants to call {string} endpoint")
    public void user_wants_to_call_endpoint(String endPoint) {
        if(endPoint.contains("@id")){
            String bookingId = ConfigReader.getConfigValue("booking.id");
            endPoint = endPoint.replace("@id",bookingId);
        }
        RestAssuredUtils.setEndPoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        RestAssuredUtils.setHeader(key, value);
    }

    @Given("set request body from the file {string}")
    public void set_request_body_from_the_file(String fileName) {
        RestAssuredUtils.setBody(fileName);
    }

    @When("user performs the post call")
    public void user_performs_the_post_call() {
        RestAssuredUtils.post();
    }

    @And("user performs the get call")
    public void userPerformsTheGetCall() {
        RestAssuredUtils.get();
    }

    @And("set request body from the file {string} and update using pojo {string}")
    public void setRequestBodyFromTheFileAndUpdateUsingPojo(String fileName, String name) throws JsonProcessingException {
        String jsonFolderPath = ConfigReader.getConfigValue("json.folder.path");
        String jsonBody = RestAssuredUtils.getDataFromFile(jsonFolderPath + fileName);
        ObjectMapper om = new ObjectMapper();
        DataPojo dataPojo = om.readValue(jsonBody, DataPojo.class);
        dataPojo.setName(name);
        RestAssuredUtils.setBodyUsingPojo(dataPojo);
    }

    @When("user performs the put call")
    public void userPerformsThePutCall() {
        RestAssuredUtils.put();
    }

    @When("user performs the delete call")
    public void userPerformsTheDeleteCall() {
        RestAssuredUtils.delete();
    }
}

