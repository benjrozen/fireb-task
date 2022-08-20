package fireb.flows;

import fireb.Context;
import fireb.api.executors.UserApiExecutor;

public class BasicFlow {
    protected Context context = Context.builder().build();
    protected UserApiExecutor userApiExecutor() {
        return UserApiExecutor.of(context);
    }
}
