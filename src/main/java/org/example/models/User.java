package org.example.models;

import org.example.controller.DatabaseReader;

public class User {
    private static final int ENCRYPTION_KEY = 0x76;
    private String email;
    private String password;
    private int privilegeLevel;

    public User(Role role) {
        this.email = null;
        this.password = null;
        this.privilegeLevel = role.ordinal();
    }

    public User(String email, String password) {
        DatabaseReader reader = new DatabaseReader();

        this.password = password;
        // TODO. From the database you get correctness from the login data, as well as the privilegeLevel from the correspondent field.
    }

    private int encrypt(String password) {
        char[] encryption = password.toCharArray();
    }

    public Role privilegeLevel() {
        return this.privilegeLevel;
    }
}
