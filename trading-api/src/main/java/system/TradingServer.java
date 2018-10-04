package system;

import api.account.AccountResource;
import api.hearbeat.HeartbeatResource;
import api.transaction.TransactionResource;
import application.AccountService;
import application.TransactionService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import persistence.AccountRepositoryInMemory;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class TradingServer implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(
            TradingServer.class.getName()
    );

    private static final String EXCEPTION_MAPPERS_PATH = "exceptionmappers";

    public static void main(String[] args) {
        new TradingServer().run();
    }

    private static HashSet<Object> getContextResources() {
        AccountService accountService = new AccountService(new AccountRepositoryInMemory());
        HashSet<Object> resources = new HashSet<>();
        AccountResource accountResource = new AccountResource(accountService);
        HeartbeatResource heartBeatResource = new HeartbeatResource();
        TransactionResource transactionResource = new TransactionResource(accountService, new TransactionService());

        resources.add(accountResource);
        resources.add(transactionResource);
        resources.add(heartBeatResource);
        return resources;
    }

    private ResourceConfig createResourceConfiguration() {
        ResourceConfig resourceConfiguration = ResourceConfig.forApplication(new Application() {
            @Override
            public Set<Object> getSingletons() {
                return getContextResources();
            }
        });

        resourceConfiguration.packages(EXCEPTION_MAPPERS_PATH);
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
