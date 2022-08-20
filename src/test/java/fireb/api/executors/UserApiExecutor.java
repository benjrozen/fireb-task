package fireb.api.executors;

import fireb.Context;
import fireb.api.users.*;

import java.util.HashMap;

public class UserApiExecutor {

    Context context;

    public UserApiExecutor(Context context) {
        this.context = context;
    }

    public static UserApiExecutor of(Context context) {
        return new UserApiExecutor(context);
    }

    public RetrieveSingleUserRequest getSingleUser(String userId) {
        return new RetrieveSingleUserRequest(context, userId);
    }

    public CreateUserRequest createUser(String userName, double balance) {
        return new CreateUserRequest(context, userName, balance);
    }

    public RetrieveMultipleUsersRequest getMultipleUsers() {
        return new RetrieveMultipleUsersRequest(context);
    }

    public RetrieveMultipleUsersRequest getMultipleUsersQuery(HashMap<String, String> queryString) {
        return new RetrieveMultipleUsersRequest(context, queryString);
    }

    public UpdateSingleUserRequest updateUser(String userId, String newUserName, double newBalance) {
        return new UpdateSingleUserRequest(context, userId, newUserName, newBalance);
    }

    public DeleteUserRequest deleteUser(String userId) {
        return new DeleteUserRequest(context, userId);
    }
}
