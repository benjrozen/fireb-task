package fireb.api.requests;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CreateUserPayload {
    String name;
    double balance;
}
