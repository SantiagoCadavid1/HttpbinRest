package org.httpbin.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.httpbin.models.HttpbinDataModel;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class HttpbinPostTask implements Task {
    private final HttpbinDataModel httpbin;
    private final String endPoint;

    public HttpbinPostTask(HttpbinDataModel httpbin, String endPoint){
        this.httpbin = httpbin;
        this.endPoint = endPoint;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(endPoint).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(httpbin).log().all()
                )
        );
    }

    public static Performable withHttpbinData(HttpbinDataModel httpbin, String endPoint){
        return instrumented(HttpbinPostTask.class,httpbin,endPoint);
    }
}
