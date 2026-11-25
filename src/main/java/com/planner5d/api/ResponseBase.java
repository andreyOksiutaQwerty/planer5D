package com.planner5d.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class ResponseBase {


    public static RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://planner5d.com")
                .addHeader("Accept", "application/json")
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .build();
    }
}
