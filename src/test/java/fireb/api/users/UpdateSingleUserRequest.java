package fireb.api.users;

import fireb.Context;
import fireb.Http;
import fireb.HttpRequest;
import fireb.api.IRequest;
import fireb.api.requests.CreateUserPayload;
import fireb.api.responses.UserResponse;
import lombok.val;

import static fireb.api.requests.BasicRequest.basicRequest;
import static fireb.api.requests.BasicRequest.successfulStatusCodes;

public class UpdateSingleUserRequest implements IRequest {

    Context context;
    String userId;
    String newUserName;
    double newBalance;

    public UpdateSingleUserRequest(Context context, String userId, String newUserName, double newBalance) {
        this.context = context;
        this.userId = userId;
        this.newUserName = newUserName;
        this.newBalance = newBalance;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + String.format("/users/%s", userId);
    }

    public HttpRequest requestDetails() {
        val userDetails = CreateUserPayload.builder()
                                           .name(newUserName)
                                           .balance(newBalance)
                                           .build();

        return basicRequest(context).payload(userDetails).responseClass(UserResponse.class);
    }

    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        if (successfulStatusCodes.contains(expectedStatusCode)) {
            return new Http(requestDetails(), expectedStatusCode).patch(url())
                                                                 .as(requestDetails().getResponseClass());
        } else {
            return new Http(requestDetails(), expectedStatusCode).patch(url());
        }
    }
}