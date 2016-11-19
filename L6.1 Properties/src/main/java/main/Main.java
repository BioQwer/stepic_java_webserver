package main;


import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import resources.ResourceServerControllerMBean;
import resources.ResourceServerController;
import resources.ResourceServlet;
import resources.TestResource;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class Main {
    public static void main(String[] args) throws Exception {
	    Server server = new Server(8080);
	    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

	    ResourceServlet resourceServlet = new ResourceServlet(new TestResource());
	    ResourceServerControllerMBean resourceServer = new ResourceServerController(resourceServlet);

	    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name = new ObjectName("Admin:type=ResourceServerController");
	    mbs.registerMBean(resourceServer, name);

	    context.addServlet(new ServletHolder(resourceServlet), "/resources");

	    HandlerList handlers = new HandlerList();
	    handlers.setHandlers(new Handler[]{context});
	    server.setHandler(handlers);

	    server.start();
	    System.out.println("Server started");
	    server.join();
    }
}
