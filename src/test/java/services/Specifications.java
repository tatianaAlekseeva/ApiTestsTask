package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class Specifications {

    private static final String BASE_URI = "http://3.73.86.8:3333/user";
    public RequestSpecification requestGetSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .log(ALL)
            .build();

    public RequestSpecification requestPostSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(JSON)
            .log(ALL)
            .build();
}
