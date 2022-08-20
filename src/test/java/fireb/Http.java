package fireb;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Http {
    HttpRequest request;
    int expectedStatusCode;

    public Http(HttpRequest request, int  expectedStatusCode) {
        this.request = request;
        this.expectedStatusCode = expectedStatusCode;
    }

    public Response get(String url) {
        return given().headers(request.getHeaders())
                      .spec(requestSpec())
                      .spec(responseSpec())
                      .get(url)
                      .then().extract().response();
    }

    public Response get(String url, HashMap<String, String> queryString) {
        return given().queryParams(queryString)
                      .headers(request.getHeaders())
                      .spec(requestSpec())
                      .spec(responseSpec())
                      .get(url)
                      .then().extract().response();
    }

    public Response post(String url) {
        Response response = given().headers(request.getHeaders())
                                   .spec(requestSpec())
                                   .spec(responseSpec())
                                   .body(serialize(request.getPayload().getBody()))
                                   .post(url)
                                   .then().extract().response();

        response.then().statusCode(expectedStatusCode);
        return response;
    }

    public Response patch(String url) {
        Response response = given().headers(request.getHeaders())
                                   .spec(requestSpec())
                                   .spec(responseSpec())
                                   .body(serialize(request.getPayload().getBody()))
                                   .patch(url)
                                   .then().extract().response();

        response.then().statusCode(expectedStatusCode);
        return response;
    }

    public Response delete(String url) {
        Response response = given().headers(request.getHeaders())
                                   .spec(requestSpec())
                                   .spec(responseSpec())
                                   .delete(url)
                                   .then().extract().response();

        response.then().statusCode(expectedStatusCode);
        return response;
    }


    RequestSpecification requestSpec() {
        RequestSpecification requestSpecification;
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecification = requestSpecBuilder.build();
        requestSpecification.config(request.getHeaderMask());
        requestSpecification.log().method();
        requestSpecification.log().uri();
        requestSpecification.log().headers();
        requestSpecification.log().body();
        return requestSpecification;
    }

    RequestSpecification responseSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addFilter(new ResponseLoggingFilter()).build();
        return requestSpecBuilder.build();
    }

    @SneakyThrows
    private String serialize(Object payload) {
        String json;
        ObjectMapper objectMapper = new ObjectMapper();
        json = objectMapper.writeValueAsString(payload);
        return json;
    }
}