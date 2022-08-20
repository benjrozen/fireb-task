package fireb;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Optional;

@Value
@Builder(toBuilder = true)
@With
public class Context {
    private String getEnvVariable(String envVariableName) {
        return Optional.ofNullable(System.getProperty(envVariableName))
                       .orElse(System.getenv(envVariableName));

    }

    String env = getEnvVariable("env");
    String email = getEnvVariable("email");
    String baseUrl = getEnvVariable("baseUrl");

    String userId;
}
