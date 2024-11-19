package org.example.models;

import org.example.drivers.DatabaseReader;

public class User {
    private static final int ENCRYPTION_KEY = 0x76;
    private final String email;
    private final int encryptedPassword;
    private final PrivilegeLevel privilegeLevel;

    public User(String email, String password) {
        DatabaseReader reader = new DatabaseReader("users.csv");
        // TODO. From the database you get correctness from the login data, as well as the privilegeLevel from the correspondent field.
    }

    private int encrypt(String password) {
        return -1 /* TODO */;
    }

    public PrivilegeLevel privilegeLevel() {
        return this.privilegeLevel;
    }
}
