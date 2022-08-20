package fireb.api;

import fireb.HttpRequest;

public interface IRequest {
    String url();
    HttpRequest requestDetails();
    Object executeExpectingStatusCode(int expectedStatusCode);
}
