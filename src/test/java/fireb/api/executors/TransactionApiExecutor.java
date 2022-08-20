package fireb.api.executors;

import fireb.Context;
import fireb.api.transactions.CreateTransactionRequest;
import fireb.api.transactions.RetrieveTransactionDetailsRequest;
import fireb.api.transactions.RetrieveTransactionStatusRequest;

public class TransactionApiExecutor {

    Context context;

    public TransactionApiExecutor(Context context) {
        this.context = context;
    }

    public static TransactionApiExecutor of(Context context) {
        return new TransactionApiExecutor(context);
    }

    public CreateTransactionRequest createTransaction(int sourceId, int destinationId, double amount) {
        return new CreateTransactionRequest(context, sourceId, destinationId, amount);
    }

    public RetrieveTransactionStatusRequest getTransactionStatus(String txId) {
        return new RetrieveTransactionStatusRequest(context, txId);
    }

    public RetrieveTransactionDetailsRequest getTransactionDetails(String txId){
        return new RetrieveTransactionDetailsRequest(context, txId);
    }
}
