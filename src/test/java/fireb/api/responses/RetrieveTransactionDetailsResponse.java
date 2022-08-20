package fireb.api.responses;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class RetrieveTransactionDetailsResponse {
    String id;
    int sourceId;
    int destinationId;
    int amount;
    String status;
    String subStatus;
    long createdAt;
    long lastUpdatedAt;
}
