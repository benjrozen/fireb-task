package fireb.api.users;

import fireb.Context;
import fireb.Http;
import fireb.HttpRequest;
import fireb.api.IRequest;
import fireb.api.responses.UserResponse;
import lombok.val;

import java.util.HashMap;

import static fireb.api.requests.BasicRequest.basicRequest;
import static fireb.api.requests.BasicRequest.successfulStatusCodes;

public class RetrieveMultipleUsersRequest implements IRequest {

    Context context;
    HashMap<String, String> queryString = new HashMap<>();

    public RetrieveMultipleUsersRequest(Context context, HashMap<String, String> queryString) {
        this.context = context;
        this.queryString = queryString;
    }

    public RetrieveMultipleUsersRequest(Context context) {
        this.context = context;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + "/users";
    }

    public HttpRequest requestDetails() {
        return basicRequest(context).responseClass(UserResponse[].class);
    }

    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        val request = new Http(requestDetails(), expectedStatusCode);
        if (queryString != null) {
            if (successfulStatusCodes.contains(expectedStatusCode)) {
                return request.get(url(), queryString).as(requestDetails().getResponseClass());
            } else {
                return new Http(requestDetails(), expectedStatusCode).get(url(), queryString);
            }
        } else {
            if (successfulStatusCodes.contains(expectedStatusCode)) {
                return request.get(url()).as(requestDetails().getResponseClass());
            } else {
                return request.get(url());
            }
        }
    }
}