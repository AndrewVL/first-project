package ru.avl.simpleweb.db.dao;

import ru.avl.simpleweb.db.datasets.UsersDataSet;
import ru.avl.simpleweb.db.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Andrey on 09.04.2017.
 */
public class UsersDAO {
    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public UsersDataSet getUser(String login, String password) throws SQLException {
        return executor.execQuery("select * from users where login='" + login +"' and password='" + password +"'", result ->
        {
            result.next();
//            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
            return new UsersDataSet(result.getLong("id"), result.getString("login"), result.getString("password"));
        });
    }

    public void insetUser(String login, String password) throws SQLException {
        executor.execUpdate("insert into users (login, password) values ('" + login + "', '" + password + "')" );
    }

    public void createUserTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, login varchar(256), " +
                "password varchar(256), primary key (id))");
    }
}
