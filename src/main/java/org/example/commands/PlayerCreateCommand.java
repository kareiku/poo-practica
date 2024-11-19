package org.example.commands;

import org.example.drivers.DatabaseWriter;
import org.example.models.Command;

public class PlayerCreateCommand extends Command {
    public void execute(String[] args) {
        new DatabaseWriter("players").create(args);
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

    protected PrivilegeLevel privilegeLevel() {
        return PrivilegeLevel.ADMIN;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
