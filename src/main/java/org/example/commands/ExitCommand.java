package org.example.commands;

import org.example.models.Command;

public class ExitCommand extends Command {
    private boolean executed;

    public void run(String[] args) {
        executed = true;
    }

    public boolean hasBeenExecuted() {
        return executed;
    }

    public void execute(String[] args) {

    }

    public String name() {
        return "exit";
    }

    public String usage() {
        return "";
    }

    public String help() {
        return "Exits the application, regardless of the current state. It does save changes.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return PrivilegeLevel.GUEST;
    }

    public boolean hasPermission() {
        return this.privilegeLevel().ordinal() <= PrivilegeLevel.GUEST.ordinal();
    }
}
