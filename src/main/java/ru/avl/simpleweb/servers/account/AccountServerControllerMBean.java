package ru.avl.simpleweb.servers.account;

/**
 * Created by Andrey on 02.05.2017.
 */
public interface AccountServerControllerMBean {
    int getUsersLimit();

    void setUsersLimit(int limit);

    int getUsers();
}
