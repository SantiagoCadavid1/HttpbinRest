package org.httpbin.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.httpbin.models.HttpbinDataModel;
import org.httpbin.questions.ObjectHttpbin;
import org.httpbin.tasks.HttpbinPostTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.httpbin.constants.Constants.URL_Base;

public class HttpbinPostStepDefinitions {
    private static final String restAPIUrl = URL_Base;

    @When("I send a request to the endpoint {string} with the key value {string}")
    public void iSendARequestToTheEndpointWithTheKeyValue(String endpoint, String value) {
        Actor user = Actor.named("user").whoCan(CallAnApi.at(restAPIUrl));
        ObjectHttpbin objectHttpbin = new ObjectHttpbin(value);
        HttpbinDataModel httpbinDataModel = objectHttpbin.answeredBy(null);

        System.out.println(httpbinDataModel);

        user.attemptsTo(
                HttpbinPostTask.withHttpbinData(httpbinDataModel, endpoint)
        );

    }
    @Then("I Validate that the response code is {string}")
    public void iValidateThatTheResponseCodeIs(String code) {

        Actor user = Actor.named("user");

        String codeRest = String.valueOf(SerenityRest.lastResponse().getStatusCode());
        user.should(
                seeThat("The responde code is",res->codeRest,equalTo(code))
        );

    }

}
