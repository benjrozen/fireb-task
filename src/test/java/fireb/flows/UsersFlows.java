package fireb.flows;


import fireb.api.responses.UserResponse;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import lombok.val;

import java.util.HashMap;

public class UsersFlows extends BasicFlow{
    Scenario scenario;

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
        RestAssured.reset();
    }

    @When("a user is created with name {string} and a balance of {int}, user creation should succeed")
    public void createValidUser(String userName, int balance) {
        val createUserResponse = (UserResponse) userApiExecutor().createUser(userName, balance).executeExpectingStatusCode(201);
        assert createUserResponse.getId() != null;
    }

    @When("a user is created with name {string} and a balance of {int}, user creation should fail," +
                  " balance cannot be a negative number when creating a user")
    public void createInvalidUserNegativeBalance(String userName, int balance) {
        userApiExecutor().createUser(userName, balance).executeExpectingStatusCode(400);
    }

    @When("a user is created with name {string} and a balance of {double}, user creation should fail, balance must be a natural number")
    public void createInvalidUserDecimalBalance(String userName, double balance) {
        userApiExecutor().createUser(userName, balance).executeExpectingStatusCode(500);
    }

    @When("all users are retrieved, a list of existing users and their details should be returned")
    public void retrieveAllUsers() {
        UserResponse[] usersResponse = (UserResponse[]) userApiExecutor().getMultipleUsers().executeExpectingStatusCode(200);
        assert usersResponse.length > 0;
    }

    @When("users are retrieved using query string param {string} {string} for existing user," +
                  " a list of the relevant users and their details should be returned")
    public void retrieveAllUsersExistingUserQueryString(String key, String value) {
        HashMap<String, String> queryString = new HashMap<>();
        queryString.put(key, value);
        val usersResponse = (UserResponse[]) userApiExecutor().getMultipleUsersQuery( queryString).executeExpectingStatusCode(200);
        assert usersResponse.length > 0;
    }

    @When("users are retrieved using query string param {string} {string} for existing user, An empty list should be returned")
    public void retrieveAllUsersNonExistentUserQueryString(String key, String value) {
        HashMap<String, String> queryString = new HashMap<>();
        queryString.put(key, value);

        val usersResponse = (UserResponse[]) userApiExecutor().getMultipleUsersQuery( queryString).executeExpectingStatusCode(200);
        assert usersResponse.length == 0;
    }

    @When("a single user is retrieved with a existing user id: {string}, user details should be returned")
    public void retrieveSingleUserExisting(String userId) {
        val userResponse = (UserResponse) userApiExecutor().getSingleUser(String.valueOf(userId)).executeExpectingStatusCode(200);
        assert userResponse.getId() != null;
    }

    @When("a single user is retrieved with a non-existent user id: {string}, User retrieval should fail")
    public void retrieveSingleUserNonExistent(String userId) {
        userApiExecutor().getSingleUser(String.valueOf(userId)).executeExpectingStatusCode(404);
    }

    @Given("existing user with id {string}, when updating user name to {string} and balance to {int}, then user name and balance should be updated")
    public void updateExistingUser(String userId, String newUserName, int newBalance) {
        userApiExecutor().updateUser(userId, newUserName, newBalance).executeExpectingStatusCode(200);

        val updatedUserResponse = (UserResponse) userApiExecutor().getSingleUser(userId).executeExpectingStatusCode(200);
        assert updatedUserResponse.getName().equals(newUserName);
        assert updatedUserResponse.getBalance() == newBalance;
    }

    @Given("existing user with id {string}, when updating user name to {string} and balance to {int}, then update user should fail, balance cannot be a negative number")
    public void updateExistingUserNegativeBalance(String userId, String newUserName, int newBalance) {
        userApiExecutor().updateUser(userId, newUserName, newBalance).executeExpectingStatusCode(400);
    }

    @Given("existing user with id {string}, when updating user name to {string} and balance to {double}, then update user should fail, balance must be a natural number")
    public void updateExistingUserDecimalBalance(String userId, String newUserName, double newBalance) {
        userApiExecutor().updateUser(userId, newUserName, newBalance).executeExpectingStatusCode(500);
    }

    @Given("non-existent user with id {string}, when updating user name to {string} and balance to {int}, then update user should fail")
    public void updateNonExistingUser(String userId, String newUserName, int newBalance) {
        userApiExecutor().updateUser( userId, newUserName, newBalance).executeExpectingStatusCode(404);
    }

    @Given("a user is created")
    public void existingUser() {
        val newUser = (UserResponse) userApiExecutor().createUser( "harry", 100).executeExpectingStatusCode(201);
        context = context.withUserId(newUser.getId().toString());
    }

    @When("the user is deleted, he should no longer appear in search")
    public void theUserIsDeletedHeShouldNoLongerAppearInSearch() {
        userApiExecutor().deleteUser(context.getUserId()).executeExpectingStatusCode(200);
        userApiExecutor().getSingleUser(context.getUserId()).executeExpectingStatusCode(404);
    }

    @Given("non-existent user id {string}")
    public void nonExistent(String userId) {
        context = context.withUserId(userId);
    }

    @When("the user is deleted, the deletion should fail")
    public void theUserIsDeletedTheDeletionShouldFail() {
        userApiExecutor().deleteUser(context.getUserId()).executeExpectingStatusCode(404);
    }
}
