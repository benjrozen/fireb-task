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

public class CreateUserRequest implements IRequest {
    Context context;
    String userName;
    double balance;

    public CreateUserRequest(Context context, String userName, double balance) {
        this.context = context;
        this.userName = userName;
        this.balance = balance;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + "/users";
    }

    public HttpRequest requestDetails() {
        val userDetails = CreateUserPayload.builder()
                                           .name(userName)
                                           .balance(balance)
                                           .build();

        return basicRequest(context).payload(userDetails).responseClass(UserResponse.class);
    }
    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        if (successfulStatusCodes.contains(expectedStatusCode)) {
            return new Http(requestDetails(), expectedStatusCode).post(url()).as(requestDetails().getResponseClass());
        }
        else {
            return new Http(requestDetails(), expectedStatusCode).post(url());
        }
    }
}
