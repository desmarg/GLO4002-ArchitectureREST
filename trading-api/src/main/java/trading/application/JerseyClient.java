package trading.application;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JerseyClient {
    private static final String URL = "http://localhost:8080";
    private static JerseyClient INSTANCE = null;

    public static JerseyClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JerseyClient();
        }

        return INSTANCE;
    }

    public <T> T getRequest(String uri, Class<T> responseType) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(this.URL + uri);
        return target.request().get(responseType);
    }
}
