package ru.avl.simpleweb.accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 08.04.2017.
 */
public class AccountService {
    
    private final Map<String, UserProfile> signUpUsersCash;
    private final Map<String, UserProfile> signInUsersCash;

    public AccountService() {
        this.signUpUsersCash = new HashMap<>();
        this.signInUsersCash = new HashMap<>();
    }
    
    public void saveUser(UserProfile user) {
        signUpUsersCash.put(user.getLogin(), user);
    }

    public UserProfile getUser(String login, String password) {
        UserProfile user = signUpUsersCash.get(login);
        if (user != null && user.getPassword().equals(password))
            return user;
        return null;
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
}
