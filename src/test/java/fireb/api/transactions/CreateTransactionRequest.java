package fireb.api.transactions;

import fireb.Context;
import fireb.Http;
import fireb.HttpRequest;
import fireb.api.IRequest;
import fireb.api.requests.CreateTransactionPayload;
import lombok.val;

import static fireb.api.requests.BasicRequest.basicRequest;
import static fireb.api.requests.BasicRequest.successfulStatusCodes;

public class CreateTransactionRequest implements IRequest {

    Context context;
    int sourceId;
    int destinationId;
    double amount;

    public CreateTransactionRequest(Context context, int sourceId, int destinationId, double amount) {
        this.context = context;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.amount = amount;
    }

    @Override
    public String url() {
        return context.getBaseUrl() + "/transactions/create";
    }

    @Override
    public HttpRequest requestDetails() {
        val transactionDetails = CreateTransactionPayload.builder()
                                                         .sourceId(sourceId)
                                                         .destinationId(destinationId)
                                                         .amount((int) amount)
                                                         .build();
        return basicRequest(context).payload(transactionDetails);
    }

    @Override
    public Object executeExpectingStatusCode(int expectedStatusCode) {
        if (successfulStatusCodes.contains(expectedStatusCode)) {
            return new Http(requestDetails(), expectedStatusCode).post(url()).then().contentType("text/html; charset=utf-8").extract().response();
        }
        else {
            return new Http(requestDetails(), expectedStatusCode).post(url());
        }
    }
}
