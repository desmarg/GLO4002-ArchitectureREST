package system;

import api.account.AccountResource;
import application.AccountService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import persistence.AccountRepositoryInMemory;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class TradingServer implements Runnable {
    private static final int PORT = 8181;

    public TradingServer() {
        this.run();
    }

    @Override
    public void run() {

        // Setup resources (API)
        AccountResource accountResource = new AccountResource(new AccountService(new AccountRepositoryInMemory()));

        // Setup API context (JERSEY + JETTY)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {

            @Override
            public Set<Object> getSingletons() {
                HashSet<Object> resourcesContext = new HashSet<>();
                resourcesContext.add(accountResource);
                return resourcesContext;
            }
        });

        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "resources");
        context.addServlet(servletHolder, "/*");

        // Setup http server
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{context});
        Server server = new Server(PORT);
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

//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
//        context.setContextPath("/");
//        Server server = new Server(PORT);
//        server.setHandler(context);
//        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);
//        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "resources");
//
//        try {
//            server.start();
//            server.join();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            server.destroy();
//        }
//    }
//
//    public static void main(String[] args)
//            throws Exception {
//
//
//}

