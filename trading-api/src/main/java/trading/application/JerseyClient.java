package trading.application;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JerseyClient {

    private final String localServerUrl = "http://localhost:8080";

    public <T> T getRequest(String uri, Class<T> responseType) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(localServerUrl + uri);
        return target.request().get(responseType);
    }
}
