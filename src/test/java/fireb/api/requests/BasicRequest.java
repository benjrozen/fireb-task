package fireb.api.requests;

import fireb.Context;
import fireb.HttpRequest;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;

import java.util.Arrays;
import java.util.List;

public class BasicRequest {

    Context context;
    public static List<Integer> successfulStatusCodes = Arrays.asList(200, 201, 301);

    public BasicRequest(Context context) {
        this.context = context;
    }


    public static HttpRequest basicRequest(Context context) {
        switch (context.getEnv()) {
            case "dev":
                return new BasicRequest(context).basicDevRequest();
            case "qa":
                // return BasicRequest for QA env
        }
        throw new NullPointerException();
    }


    public HttpRequest basicDevRequest() {
        return new HttpRequest().headerMask(RestAssured.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                                .header("accept", "*/*")
                                .header("Authorization", "Bearer " + context.getEmail())
                                .header("Content-Type", "application/json");
    }
}
