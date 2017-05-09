package ru.avl.simpleweb.servers.account;

/**
 * Created by Andrey on 02.05.2017.
 */
public class AccountServerController implements AccountServerControllerMBean {
    private AccountServer accountServer;

    public AccountServerController(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int limit) {
        accountServer.setUsersLimit(limit);
    }

    @Override
    public int getUsers() {
        return accountServer.getUsersCount();
    }
}
