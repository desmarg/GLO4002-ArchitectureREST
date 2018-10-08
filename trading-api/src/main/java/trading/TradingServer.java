package trading;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.jackson.JacksonFeature;
import trading.api.resource.AccountResource;
import trading.api.resource.ReportResource;
import trading.api.resource.TransactionResource;
import trading.api.configuration.CustomJsonProvider;
import trading.services.Services;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class TradingServer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(
            TradingServer.class.getName()
    );

    private static final String EXCEPTION_MAPPERS_PATH = "trading.exception";

    public static void main(String[] args) {
        new TradingServer().run();
    }

    private static HashSet<Object> getContextResources() {
        HashSet<Object> resources = new HashSet<>();
        Services services = new Services();
        AccountResource accountResource = new AccountResource(services);
        TransactionResource transactionResource = new TransactionResource(services);
        ReportResource reportResource = new ReportResource(services);

        resources.add(accountResource);
        resources.add(transactionResource);
        resources.add(reportResource);
        return resources;
    }

    private ResourceConfig createResourceConfiguration() {
        ResourceConfig resourceConfiguration = ResourceConfig.forApplication(new Application() {
            @Override
            public Set<Object> getSingletons() {
                return getContextResources();
            }
        });

        // Enable exception mapper.
        resourceConfiguration.packages(EXCEPTION_MAPPERS_PATH);
        // Enable jackson support.
        resourceConfiguration.register(JacksonFeature.class);
        resourceConfiguration.register(CustomJsonProvider.class);
        return resourceConfiguration;
    }

    @Override
    public void run() {
        // Get port ENV variable, if it is set, else use default port.
        String portStr = System.getenv("TRADING_API_PORT");
        int port = 8181;
        if (portStr != null) {
            port = Integer.parseInt(portStr);
            LOGGER.info("Using port " + port);
        } else {
            LOGGER.info("TRADING_API_PORT not set, using default port " + port);
        }

        Server server = new Server(port);
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/");
        ResourceConfig resourceConfiguration = this.createResourceConfiguration();
        ServletContainer container = new ServletContainer(resourceConfiguration);
        ServletHolder servletHolder = new ServletHolder(container);
        servletContextHandler.addServlet(servletHolder, "/*");
        /*ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        Server server = new Server(port);
        server.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "api.views");*/

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }
    }
}
