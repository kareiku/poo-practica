package org.example.views.commands;

import org.example.views.Command;

public class HelpCommand extends Command {
    private final Command command;

    public HelpCommand(Command command) {
        this.command = command == null ? this : command;
    }

    public void execute(String[] args) {
    }

    public String name() {
        return "help";
    }

    public String usage() {
        return "<command>";
    }

    public String help() {
        return "Displays help information for each command.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return PrivilegeLevel.GUEST;
    }

    public boolean hasPermission() {
        return this.privilegeLevel().ordinal() <= PrivilegeLevel.GUEST.ordinal();
    }
}
