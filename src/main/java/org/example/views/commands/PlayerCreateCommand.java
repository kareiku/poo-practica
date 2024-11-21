package org.example.views.commands;

import org.example.controller.DatabaseWriter;
import org.example.views.Command;
import org.example.models.Role;

public class PlayerCreateCommand extends Command {
    public void execute(String[] args) {
        new DatabaseWriter().create(args);
    }

    public String name() {
        return "player-create";
    }

    public String usage() {
        return "<DNI>;<name>;<surnames>";
    }

    public String help() {
        return "Creates a player in the system.";
    }

    protected int privilegeLevel() {
        return Role.ADMIN.ordinal();
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == Role.ADMIN.ordinal();
    }
}
