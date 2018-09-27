package api.hearbeat;

import java.time.OffsetDateTime;

public class HeartbeatResponse {
    public final String token;
    public final OffsetDateTime time;

    public HeartbeatResponse(String token) {
        this.token = token;
        this.time = OffsetDateTime.now();
    }
}
