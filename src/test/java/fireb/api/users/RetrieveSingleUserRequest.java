package fireb.api.users;

import fireb.Context;
import fireb.Http;
import fireb.HttpRequest;
import fireb.api.IRequest;
import fireb.api.responses.UserResponse;

import static fireb.api.requests.BasicRequest.basicRequest;
import static fireb.api.requests.BasicRequest.successfulStatusCodes;

public class RetrieveSingleUserRequest implements IRequest {

    Context context;
    String userId;

    public RetrieveSingleUserRequest(Context context, String userId) {
        this.context = context;
        this.userId = userId;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + String.format("/users/%s", userId) ;
    }

    @Override
    public HttpRequest requestDetails() {
        return basicRequest(context).responseClass(UserResponse.class);
    }

    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        if (successfulStatusCodes.contains(expectedStatusCode)) {
            return new Http(requestDetails(), expectedStatusCode).get(url())
                                                                 .as(requestDetails().getResponseClass());
        }
        else {
            return new Http(requestDetails(), expectedStatusCode).get(url());
        }
    }
}
