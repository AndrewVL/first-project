package ru.avl.simpleweb;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import ru.avl.simpleweb.accounts.AccountService;
import ru.avl.simpleweb.accserver.AccountServer;
import ru.avl.simpleweb.accserver.AccountServerController;
import ru.avl.simpleweb.accserver.AccountServerControllerMBean;
import ru.avl.simpleweb.accserver.AccountServerImpl;
import ru.avl.simpleweb.servlets.*;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by Andrey on 01.04.2017.
 */
public class Launcher {

    private static final Logger logger = Log.getLogger(Launcher.class);

    public static void main(String[] args) throws Exception {
        MirrorRequestServlet mirrorServlet = new MirrorRequestServlet();

        AccountService accountService = new AccountService();
        SignUpServlet signUpServlet = new SignUpServlet(accountService);
        SignInServlet signInServlet = new SignInServlet(accountService);

        AccountServer accountServer = new AccountServerImpl(10);
        AccServerServlet accServerServlet = new AccServerServlet(accountServer);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(accServerServlet), "/admin");

        AccountServerControllerMBean mBean = new AccountServerController(accountServer);
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        mBeanServer.registerMBean(mBean, new ObjectName("Admin:type=AccountServerController.usersLimit"));

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        logger.info("Server started");
        server.join();
    }
}
