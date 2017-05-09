package ru.avl.simpleweb.servers.account;

/**
 * Created by Andrey on 02.05.2017.
 */
public interface AccountServer {
    void addNewUser();

    void removeUser();

    int getUsersLimit();

    void setUsersLimit(int limit);

    int getUsersCount();
}
