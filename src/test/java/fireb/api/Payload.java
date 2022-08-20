package fireb.api;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Payload {
    Object body;
    Object payloadClass;

    public static Payload of(Object payload) {
        return Payload.builder().body(payload).payloadClass(payload).build();
    }
}
