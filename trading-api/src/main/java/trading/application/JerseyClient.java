package trading.application;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JerseyClient {
    private final String URL = "http://localhost:8080";

    public <T> T getRequest(String uri, Class<T> responseType) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(this.URL + uri);
        return target.request().get(responseType);
    }
}
