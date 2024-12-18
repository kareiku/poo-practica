package org.example.models;

import org.example.utils.Role;

public class User {
    private final String email;
    private final String password;
    private final Role role;

    public User(Role role) {
        this(null, null, role);
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String email() {
        return this.email;
    }

    public Role role() {
        return this.role;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }
}
