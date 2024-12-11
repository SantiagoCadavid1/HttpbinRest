package org.httpbin.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.httpbin.models.HttpbinDataModel;

public class ObjectHttpbin implements Question <HttpbinDataModel> {
    private final String key;

    public ObjectHttpbin(String key){
        this.key = key;
    }

    @Override
    public HttpbinDataModel answeredBy(Actor actor){
        HttpbinDataModel httpbin =HttpbinDataModel.builder().key(key).build();

        return httpbin;
    }


}
