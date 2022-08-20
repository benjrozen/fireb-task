package fireb;

import fireb.api.Payload;
import io.restassured.config.RestAssuredConfig;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.HashMap;

@Getter
public class HttpRequest {
    HashMap<String, String> headers = new HashMap<>();
    RestAssuredConfig headerMask;
    Payload payload;
    HashMap<String, String> queryString;
    Type responseClass;

    public HttpRequest() {
    }

    public HttpRequest headerMask(RestAssuredConfig headerMask) {
        this.headerMask = headerMask;

        return this;
    }

    public HttpRequest header(String key, String value) {
        this.headers.put(key, value);

        return this;
    }

    public HttpRequest payload(Object payload) {
        this.payload = Payload.of(payload);

        return this;
    }

    public HttpRequest responseClass(Type responseClass) {
        this.responseClass = responseClass;

        return this;
    }
}
