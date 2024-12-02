package org.example.controllers;

import org.example.models.Database;
import org.example.models.Error;
import org.example.models.Role;
import org.example.models.User;

import java.util.Deque;

public class Authenticator {
    private User user = null;
    private Role role = Role.GUEST;

    public Error login(String email, String password) {
        if (this.isLoggedIn()) {
            return Error.ALREADY_LOGGED;
        } else if (this.existsUser(email, password)) {
            this.user = new User(email, password, this.role);
            return Error.NONE;
        } else return Error.NO_SUCH_USER;
    }

    public Error logout() {
        if (this.isLoggedIn()) {
            this.user = null;
            this.role = Role.GUEST;
            return Error.NONE;
        } else return Error.NOT_LOGGED_IN;
    }

    private boolean isLoggedIn() {
        return this.user != null && !this.user.hasRole(Role.GUEST);
    }

    private boolean existsUser(String email, String password) {
        final Deque<String> data = new Database("users.csv").loadData();
        boolean exists = false;
        while (!data.isEmpty()) {
            String element = data.pop();
            exists = element.startsWith(email + "," + password);
        }
        return exists;
    }

    public static boolean hasPermission(Role[] roles) {
        boolean hasPermission = false;
        for (Role role : roles) {
            if (!hasPermission) {
                hasPermission = this.role == role;
            }
        }
        return hasPermission;
    }
}
