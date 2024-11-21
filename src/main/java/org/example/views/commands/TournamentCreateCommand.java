package org.example.views.commands;

import org.example.views.Command;

public class TournamentCreateCommand extends Command {
    public void execute(String[] args) {
        // TODO
    }

    public String name() {
        return "tournament-create";
    }

    public String usage() {
        return "<name>;<start date>;<end date>;<league>;<sport>";
    }

    public String help() {
        return "Creates a tournament.";
    }

    protected PrivilegeLevel privilegeLevel() {
        return /* Fixme. Again and again... */ null;
    }

    public boolean hasPermission() {
        return this.privilegeLevel() == PrivilegeLevel.ADMIN;
    }
}
