package org.example.models;

public class User {
    private String email;
    private String password;
    private Role role;

    public User(Role role) {
        this(null, null);
        this.role = role;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
}
