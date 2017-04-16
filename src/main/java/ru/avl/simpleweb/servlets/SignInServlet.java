package ru.avl.simpleweb.servlets;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import ru.avl.simpleweb.accounts.AccountService;
import ru.avl.simpleweb.accounts.UserProfile;
import ru.avl.simpleweb.db.datasets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Andrey on 08.04.2017.
 */
public class SignInServlet extends HttpServlet {

    private static final Logger logger = Log.getLogger(SignInServlet.class);
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password ==null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        UsersDataSet usersData = null;
        try {
            usersData = accountService.getUser(login, password);
        } catch (SQLException e) {
            logger.warn(e);
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().println("Unauthorized");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.signInUser(req.getSession().getId(),
                new UserProfile(usersData.getLogin(),usersData.getPassword(), "defaultemail@gmail.com"));
//        Gson gson = new Gson();
//        String json = gson.toJson(usersData);
//        resp.getWriter().println(json);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("Authorized: " + login);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
