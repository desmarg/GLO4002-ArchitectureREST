package system;

import api.account.AccountResource;
import application.AccountService;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import persistence.AccountRepositoryInMemory;


public class TradingServer implements Runnable {
    private static final int PORT = 8181;

    public static void main(String[] args) {
        new TradingServer().run();
    }

    public void run() {

        // Setup resources (API)
        AccountResource accountResource = new AccountResource(new AccountService(new
                AccountRepositoryInMemory()));

        // Setup API context (JERSEY + JETTY)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {

            @Override
            public Set<Object> getSingletons() {
                HashSet<Object> resourcesContext = new HashSet<>();
                resourcesContext.add(accountResource);
                return resourcesContext;
            }
        });

        Server server = new Server(PORT);

        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");

        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);

        servletHolder.setInitParameter("jersey.config.server.provider.packages", "resources");
        context.addServlet(servletHolder, "/*");
        contextHandler.addServlet(servletHolder, "/*");

        // Setup resources (API)

        // Setup http server
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{context});
        server.setHandler(contexts);

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
