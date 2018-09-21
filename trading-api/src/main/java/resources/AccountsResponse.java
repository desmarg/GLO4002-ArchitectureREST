package resources;

import java.time.OffsetDateTime;


public class AccountsResponse {
    public final String token;
    public final OffsetDateTime time;

    public AccountsResponse() {
        this.token = "Test";
        this.time = OffsetDateTime.now();
    }
}
