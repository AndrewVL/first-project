package ru.avl.simpleweb;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import ru.avl.simpleweb.accounts.AccountService;
import ru.avl.simpleweb.servlets.MirrorRequestServlet;
import ru.avl.simpleweb.servlets.SignInServlet;
import ru.avl.simpleweb.servlets.SignUpServlet;

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

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(signInServlet), "/signin");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        logger.info("Server started");
        server.join();
    }
}
