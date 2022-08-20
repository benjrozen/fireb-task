package fireb.api.users;

import fireb.Context;
import fireb.Http;
import fireb.HttpRequest;
import fireb.api.IRequest;
import fireb.api.responses.EmptyResponse;

import static fireb.api.requests.BasicRequest.basicRequest;

public class DeleteUserRequest implements IRequest {
    Context context;
    String userId;

    public DeleteUserRequest(Context context, String userId) {
        this.context = context;
        this.userId = userId;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + String.format("/users/%s", userId);

    }

    @Override
    public HttpRequest requestDetails() {
        return basicRequest(context).responseClass(EmptyResponse.class);
    }

    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        return new Http(requestDetails(), expectedStatusCode).delete(url());
    }
}
