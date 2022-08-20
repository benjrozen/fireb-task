package fireb.api.transactions;

import fireb.Context;
import fireb.Http;
import fireb.HttpRequest;
import fireb.api.IRequest;
import fireb.api.responses.RetrieveTransactionDetailsResponse;

import static fireb.api.requests.BasicRequest.basicRequest;
import static fireb.api.requests.BasicRequest.successfulStatusCodes;

public class RetrieveTransactionDetailsRequest implements IRequest {

    Context context;
    String txId;

    public RetrieveTransactionDetailsRequest(Context context, String txId) {
        this.context = context;
        this.txId = txId;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + String.format("/transactions/%s", txId);
    }

    @Override
    public HttpRequest requestDetails() {
        return basicRequest(context).responseClass(RetrieveTransactionDetailsResponse.class);
    }

    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        if (successfulStatusCodes.contains(expectedStatusCode)) {
            return new Http(requestDetails(), expectedStatusCode).get(url()).then().extract().response()
                                                                 .as(requestDetails().getResponseClass());
        } else {
            return new Http(requestDetails(), expectedStatusCode).get(url());
        }
    }
}
