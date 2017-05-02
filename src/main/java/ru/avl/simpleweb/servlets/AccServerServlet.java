package ru.avl.simpleweb.servlets;

import ru.avl.simpleweb.accserver.AccountServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Andrey on 02.05.2017.
 */
public class AccServerServlet extends HttpServlet {
    private AccountServer accountServer;

    public AccServerServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(accountServer.getUsersLimit());
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
