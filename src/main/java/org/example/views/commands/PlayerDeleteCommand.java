package org.example.views.commands;

import org.example.views.Command;

public class PlayerDeleteCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "player-delete";
    }

    public String usage() {
        return "<DNI>";
    }

    public String help() {
        return "Removes a player from the system.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return PrivilegeLevel.PLAYER /* Fixme. WAIT! Isn't current privilege level always extracted from currently-logged user? */;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
