package ru.avl.simpleweb;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import ru.avl.simpleweb.accounts.AccountService;
import ru.avl.simpleweb.servers.account.AccountServer;
import ru.avl.simpleweb.servers.account.AccountServerController;
import ru.avl.simpleweb.servers.account.AccountServerControllerMBean;
import ru.avl.simpleweb.servers.account.AccountServerImpl;
import ru.avl.simpleweb.servers.resource.ResourceServer;
import ru.avl.simpleweb.servers.resource.ResourceServerController;
import ru.avl.simpleweb.servers.resource.ResourceServerControllerMBean;
import ru.avl.simpleweb.servers.resource.ResourceServerImpl;
import ru.avl.simpleweb.servlets.*;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Launcher {

    private static final Logger logger = Log.getLogger(Launcher.class);

    public static void main(String[] args) throws Exception {
        MirrorRequestServlet mirrorServlet = new MirrorRequestServlet();

        AccountService accountService = new AccountService();
        SignUpServlet signUpServlet = new SignUpServlet(accountService);
        SignInServlet signInServlet = new SignInServlet(accountService);

        AccountServer accountServer = new AccountServerImpl(10);
        AccServerServlet accServerServlet = new AccServerServlet(accountServer);

        ResourceServer resourceServer = new ResourceServerImpl();
        ResourceServlet resourceServlet = new ResourceServlet(resourceServer);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(accServerServlet), "/admin");
        context.addServlet(new ServletHolder(resourceServlet), "/resources");

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        AccountServerControllerMBean accountMBean = new AccountServerController(accountServer);
        ResourceServerControllerMBean resourceMBean = new ResourceServerController(resourceServer);
        mBeanServer.registerMBean(accountMBean, new ObjectName("Admin:type=AccountServerController.usersLimit"));
        mBeanServer.registerMBean(resourceMBean, new ObjectName("Admin:type=ResourceServerController"));

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        logger.info("Server started");
        server.join();
    }
}
