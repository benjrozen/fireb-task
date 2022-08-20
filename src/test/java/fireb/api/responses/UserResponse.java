package fireb.api.responses;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserResponse {
    Integer id;
    String name;
    Integer balance;
}
