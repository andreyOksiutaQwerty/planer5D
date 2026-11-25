package com.planner5d.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CRUDsAPI {

    public static Response getResponse(String projectKey){
        return given(ResponseBase.baseSpec())
                .when()
                .get("/api/project/"+projectKey);
    }
}
