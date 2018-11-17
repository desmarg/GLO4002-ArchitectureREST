package trading.application;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JerseyClient {

    public <T> T getRequest(String uri, Class<T> responseType) {
        Client client = ClientBuilder.newClient();
        String URL = "http://localhost:8080";
        WebTarget target = client.target(URL + uri);
        return target.request().get(responseType);
    }
}
