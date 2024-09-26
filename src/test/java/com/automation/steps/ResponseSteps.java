package com.automation.steps;

import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify the status code is {int}")
    public void verify_the_status_code_is(Integer statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @Then("verify the booking id is not empty")
    public void verify_the_booking_id_is_not_empty() {
        String bookingID = RestAssuredUtils.getResponseFieldValue("id");
        Assert.assertTrue(!bookingID.isEmpty());
    }

    @Then("stores the id into {string}")
    public void stores_the_id_into(String key) {
        ConfigReader.setConfigValue(key,RestAssuredUtils.getResponseFieldValue("id"));
    }
}
