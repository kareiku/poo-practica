package org.example.commands;

import org.example.models.Command;
import org.example.views.CommandView;

public class HelpCommand extends Command {
    private final Command command;

    public HelpCommand(Command command) {
        this.command = command == null ? this : command;
    }

    public void execute(String[] args) {
        new CommandView().write(command);
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
