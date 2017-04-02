package ru.avl.simpleweb;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import ru.avl.simpleweb.servlets.MirrorRequestServlet;

/**
 * Created by Andrey on 01.04.2017.
 */
public class Launcher {

    private static final Logger LOG = Log.getLogger(Launcher.class);

    public static void main(String[] args) throws Exception {
        MirrorRequestServlet mirrorServlet = new MirrorRequestServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        LOG.info("Server started");
        server.join();
    }
}
