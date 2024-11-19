package org.example.commands;

import org.example.models.Command;

public class LoginCommand extends Command {
    public void execute(String[] args) {
        /*
         TODO?
          - Search for the user in the database (file-based first, relational later).
          - If it's a valid user, get its privilege level.
          - Set the user privilege to role.
        */
    }

    public String name() {
        return "login";
    }

    public String usage() {
        return "<email>;<password>";
    }

    public String help() {
        return "Attempts to log the specified user into the system.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Here comes the first problem: the privilege level in here changes when getting the user from the database. */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.GUEST;
    }
}
