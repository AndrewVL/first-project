package ru.avl.simpleweb.servlets;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import resources.TestResource;
import ru.avl.simpleweb.sax.ReadXMLFileSAX;
import ru.avl.simpleweb.servers.resource.ResourceServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {
    private static final Logger logger = Log.getLogger(ResourceServlet.class);
    private ResourceServer resourceServer;

    public ResourceServlet(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null || path.equals("")) {
            logger.warn("request sent by the client was syntactically incorrect");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        resourceServer.setResource((TestResource) ReadXMLFileSAX.readXML(path));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
