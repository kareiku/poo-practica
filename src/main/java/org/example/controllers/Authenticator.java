package org.example.controllers;

import org.example.models.Database;
import org.example.models.Error;
import org.example.models.Role;
import org.example.models.User;

import java.util.HashSet;
import java.util.Set;

public class Authenticator {
    private final Set<User> users;
    private User currentUser;

    public Authenticator() {
        this.users = new HashSet<>();
        this.currentUser = new User(Role.GUEST);
        this.loadUsers(new Database("users.csv"));
    }

    public Error login(String email, String password) {
        if (this.isLoggedIn()) {
            return Error.ALREADY_LOGGED;
        } else if (this.users.contains(email, password)) {
            this.currentUser = new User(email, password, this.currentUser.getRole());
            return Error.NONE;
        } else return Error.NO_SUCH_USER;
    }

    public Error logout() {
        if (this.isLoggedIn()) {
            this.currentUser = new User(Role.GUEST);
            return Error.NONE;
        } else return Error.NOT_LOGGED_IN;
    }

    private boolean isLoggedIn() {
        return this.currentUser != null && !(this.currentUser.getRole() == Role.GUEST));
    }

    public Role currentUserRole() {
        return this.currentUser.getRole();
    }

    private void loadUsers(Database database) {
    }
}
