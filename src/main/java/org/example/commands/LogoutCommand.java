package org.example.commands;

import org.example.models.Command;

public class LogoutCommand extends Command {
    public void execute(String[] args) {
        // TODO: Set the User to new User or null or something.
    }

    public String name() {
        return "logout";
    }

    public String usage() {
        return "";
    }

    public String help() {
        return "Attempts to log off the current user from the system, if logged.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme: alike LoginCommand's problem. */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() != PrivilegeLevel.GUEST;
    }
}
