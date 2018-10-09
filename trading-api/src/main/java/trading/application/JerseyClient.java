package trading.application;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JerseyClient {
    private static JerseyClient INSTANCE = null;

    public static JerseyClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new JerseyClient();
        }

        return INSTANCE;
    }

    public <T> T getRequest(String uri, Class<T> responseType) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080" + uri);
        return target.request().get(responseType);
    }
}
