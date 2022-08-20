package fireb.api.transactions;

import fireb.Context;
import fireb.Http;
import fireb.HttpRequest;
import fireb.api.IRequest;

import static fireb.api.requests.BasicRequest.basicRequest;
import static fireb.api.requests.BasicRequest.successfulStatusCodes;

public class RetrieveTransactionStatusRequest implements IRequest {

    Context context;
    String txId;

    public RetrieveTransactionStatusRequest(Context context, String txId) {
        this.context = context;
        this.txId = txId;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + String.format("/transactions/status/%s", txId);
    }

    @Override
    public HttpRequest requestDetails() {
        return basicRequest(context);
    }

    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        if (successfulStatusCodes.contains(expectedStatusCode)) {
            return new Http(requestDetails(), expectedStatusCode).get(url()).then().contentType("text/html; charset=utf-8")
                                                                 .extract().response();
        }
        else {
            return new Http(requestDetails(), expectedStatusCode).get(url());
        }
    }
}
