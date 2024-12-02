package org.example.models;

public class User {
    private final String email;
    private final String password;
    private final Role role;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public boolean hasRole(Role role) {
        return this.role == role;
    }
}
