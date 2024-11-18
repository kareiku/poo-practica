package org.example.commands;

import org.example.models.Command;
import org.example.models.Role;

public class LoginCommand extends Command {
    private Role role;

    public LoginCommand() {
        super(
                "login",
                "<email>;<password>}",
                "Attempts to log the specified user into the system."
        );
    }

    public void run(String[] args) {
        /*
         TODO
          - Search for the user in the database (file-based first, relational later)
          - If it's a valid user, get its privilege level
          - Set the user privilege to role
        */
    }

    public Role getRole() {
        return role;
    }
}