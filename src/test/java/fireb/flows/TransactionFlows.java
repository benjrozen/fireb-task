package fireb.flows;

import fireb.api.executors.TransactionApiExecutor;
import fireb.api.responses.UserResponse;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.val;

public class TransactionFlows extends BasicFlow{
    Scenario scenario;

    protected TransactionApiExecutor transactionApiExecutor() {
        return TransactionApiExecutor.of(context);
    }

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        RestAssured.reset();
    }

    @Given("a valid transaction is created with sourceId: {int}, destinationId {int}, amount {int} - then transaction id should be returned," +
                   " amount provided should be deducted from source and added to destination")
    public void createValidTransactionAndVerify(int sourceId, int destinationId, int amount) {
        val getSourceDetailsBefore = (UserResponse) userApiExecutor().getSingleUser(String.valueOf(sourceId))
                                                                     .executeExpectingStatusCode(200);
        val getDestDetailsBefore = (UserResponse) userApiExecutor().getSingleUser(String.valueOf(destinationId))
                                                                   .executeExpectingStatusCode(200);;

        transactionApiExecutor().createTransaction(sourceId, destinationId, amount).executeExpectingStatusCode(201);;

        val getSourceDetailsAfter = (UserResponse) userApiExecutor().getSingleUser(String.valueOf(sourceId))
                                                                    .executeExpectingStatusCode(200);;
        val getDestDetailsAfter = (UserResponse) userApiExecutor().getSingleUser(String.valueOf(destinationId))
                                                                  .executeExpectingStatusCode(200);;

        assert getSourceDetailsBefore.getBalance() - getSourceDetailsAfter.getBalance() == amount;
        assert getDestDetailsAfter.getBalance() - getDestDetailsBefore.getBalance() == amount;
    }


    @Given("invalid transaction is created with sourceId: {int}, destinationId {int}, amount {double} - then transaction should fail," +
                   " amount must be a natural number")
    public void createInValidTransactionAmountIsDecimal(int sourceId, int destinationId, double amount) {
        transactionApiExecutor().createTransaction(sourceId, destinationId, amount).executeExpectingStatusCode(201);;
    }

    @Given("a user is created with balance of {int}")
    public void aUserIsCreatedWithBalanceOf(int balance) {
        val newUserResponse = (UserResponse) userApiExecutor().createUser("Bobby", balance).executeExpectingStatusCode(201);
        context = context.withUserId(newUserResponse.getId().toString());
    }

    @When("invalid transaction is created with sourceId: {int}, destinationId of user created, amount {int} - then transaction should fail," +
                  " amount cannot be more than destination can afford")
    public void createInValidTransactionAmountDestinationThanSourceCanAfford(int destinationId, int amount) {
        val createTransactionResponse = (Response) transactionApiExecutor().createTransaction(Integer.parseInt(context.getUserId()), destinationId, amount).executeExpectingStatusCode(201);;
        String txId = createTransactionResponse.getBody().asString();

        val getTransactionStatus = (Response) transactionApiExecutor().getTransactionStatus(txId).executeExpectingStatusCode(200);
        assert getTransactionStatus.getBody().asString().equals("FAILED");
    }

    @When("invalid transaction is created with sourceId of user created, destinationId {int}, amount {int} - then transaction should fail," +
                  " amount cannot be more than source can afford")
    public void createInValidTransactionAmountHigherThanSourceCanAfford(int destinationId, int amount) {
        val createTransactionResponse = (Response) transactionApiExecutor().createTransaction(Integer.parseInt(context.getUserId()), destinationId, amount).executeExpectingStatusCode(201);;
        String txId = createTransactionResponse.getBody().asString();

        val getTransactionStatus = (Response) transactionApiExecutor().getTransactionStatus(txId).executeExpectingStatusCode(200);
        assert getTransactionStatus.getBody().asString().equals("FAILED");
    }

    @Given("invalid transaction is created with sourceId: {int}, destinationId {int}, amount {double} - then transaction should fail")
    public void aValidTransactionIsCreatedWithSourceIdDestinationIdAmountThenTransactionShouldFail(int sourceId, int destinationId, double amount) {
        transactionApiExecutor().createTransaction(sourceId, destinationId, amount).executeExpectingStatusCode(500);
    }

    @Given("transaction status is retrieved for existing txId: {string}, Then transaction status should be returned")
    public void retrieveTransactionStatusExisting(String txId) {
        transactionApiExecutor().getTransactionStatus(txId).executeExpectingStatusCode(200);
    }

    @Given("transaction status is retrieved for non-existent txId: {string}, Then transaction status retrieval should fail")
    public void retrieveTransactionStatusNonExisting(String txId) {
        transactionApiExecutor().getTransactionStatus(txId).executeExpectingStatusCode(404);
    }

    @Given("transaction details are retrieved for existing txId: {string}, Then transaction status should be returned")
    public void retrieveTransactionDetailsExisting(String txId) {
        transactionApiExecutor().getTransactionDetails(txId).executeExpectingStatusCode(200);
    }

    @Given("transaction details are retrieved for non-existent txId: {string}, Then transaction status retrieval should fail")
    public void retrieveTransactionDetailsNonExisting(String txId) {
        transactionApiExecutor().getTransactionDetails(txId).executeExpectingStatusCode(404);
    }
}