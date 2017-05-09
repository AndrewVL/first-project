package ru.avl.simpleweb.servers.account;

/**
 * Created by Andrey on 02.05.2017.
 */
public class AccountServerImpl implements AccountServer {
    private int usersLimit;
    private int usersCount = 0;

    public AccountServerImpl(int limit) {
        usersLimit = limit;
    }

    @Override
    public void addNewUser() {
        usersCount++;
    }

    @Override
    public void removeUser() {
        if (usersCount > 0)
            usersCount--;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public void setUsersLimit(int limit) {
        this.usersLimit = limit;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }
}
