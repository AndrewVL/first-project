package ru.avl.simpleweb.accounts;

import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.h2.jdbcx.JdbcDataSource;
import ru.avl.simpleweb.db.dao.UsersDAO;
import ru.avl.simpleweb.db.datasets.UsersDataSet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 08.04.2017.
 */
public class AccountService {

    private static final Logger logger = Log.getLogger(AccountService.class);

    private final Connection connection;
    private UsersDAO usersDAO;
    private final Map<String, UserProfile> signInUsersCash;

    public AccountService() {
        this.connection = getH2Connection();
        this.usersDAO = new UsersDAO(connection);
        try {
            usersDAO.createUserTable();
        } catch (SQLException e) {
            logger.warn(e);
        }
        this.signInUsersCash = new HashMap<>();
    }
    
    public void saveUser(String login, String password) throws SQLException {
        usersDAO.insetUser(login, password);
    }

    public UsersDataSet getUser(String login, String password) throws SQLException {
        UsersDataSet user = usersDAO.getUser(login, password);
        return user;
    }

    public void signInUser(String sessionId, UserProfile user) {
        signInUsersCash.put(sessionId, user);
    }

    public UserProfile getSignInUser(String sessionId) {
        return signInUsersCash.get(sessionId);
    }

    public void SignOutUser(String sessionId) {
        signInUsersCash.remove(sessionId);
    }

    private Connection getH2Connection() {
        String url = "jdbc:h2:./h2db";
        String name = "test";
        String pass = "test";

        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(url);
        ds.setUser(name);
        ds.setPassword(pass);
        try {
            Connection con = ds.getConnection();
            return ds.getConnection();
        } catch (SQLException e) {
            logger.warn(e);
        }
        return null;
    }
}
