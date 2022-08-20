package fireb.api.requests;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class CreateTransactionPayload {
    int sourceId;
    int destinationId;
    int amount;
}
